package com.ershiyi.service.impl;

import com.alibaba.fastjson.JSON;
import com.ershiyi.Utils.DateUtils;
import com.ershiyi.Utils.DecimalUtils;
import com.ershiyi.Utils.SizeUtils;
import com.ershiyi.Utils.SwitchQuestionUtils;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.dto.StudyRecordDTO;
import com.ershiyi.mapper.LearnMapper;
import com.ershiyi.service.LearnService;
import com.ershiyi.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import twenty.alp.TimeCalculate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * @Description: 学科课堂服务层实现类
 * @author: zss98
 * @date: 2020-08-05 11:40
 * @version: 1.0
 */
@Service
@PropertySource("classpath:application.yml")
public class LearnServiceImpl<T> implements LearnService {

    @Resource
    private LearnMapper mapper;

    @Resource
    private LearnServiceImpl service;

    @Value("#{'${question.rule}'.split(',')}")
    private List<Integer> list;


    /**
     * 根据学生编号查询学生课程列表
     *
     * @param studenterId 学生编号
     * @param pageNumber  页码
     * @param pageSize    每页展示的数量
     * @return
     */
    @Override
    public PageInfo<CoursePojo> courseList(String studenterId, Integer pageNumber, Integer pageSize) {
        // 开启分页
        PageHelper.startPage(pageNumber, pageSize);
        // 根据学生编号查询出学生所有的课程id集合
        List<CoursePojo> results = mapper.findAllCourse(studenterId);
        return new PageInfo<>(results);
    }

    @Override
    public List<ChapterMenu> chapterMenu(RequestDTO request) {
        // 将学生浏览该课程存入到历史记录中
        mapper.addHistory(request.getStudenterId(), request.getCourseId());
        return service.getStudyStatus(request.getStudenterId(), mapper.knowledgeMenu(request));
    }

    /**
     * 点在学习笔记
     * @param request
     * @return
     */
    @Override
    public int likeNote(NoteInfo request) {
        // 插入当条记录
        mapper.insertLikeNote(request.getId(),request.getStudenterId());
        // 当前标签点赞数加1
        return mapper.addLike(request.getId());
    }

    /**
     * 发表学习笔记
     * @param request
     * @return
     */
    @Override
    public NoteInfo pushNote(NoteInfo request) {
        // 获取学生头像和昵称
        StudentInformation info = mapper.getStudentInfo(request);
        request.setSendImg(info.getUserImage());
        request.setSendName(info.getName());
        request.setSendTime(DateUtils.formatTime());
        mapper.insertNote(request);
        return request;
    }

    /**
     * 获取共享学习笔记内容列表
     * @param request
     * @return
     */
    @Override
    public List<NoteInfo> noteList(RequestDTO request) {
        List<NoteInfo> result = mapper.noteList(request.getKnowId(), request.getPageNumber() - 1, request.getPageSize());
        result.forEach(res -> res.setIsLike(mapper.isLike(res.getId(),request.getStudenterId()).isEmpty() ? 0:1));
        return result;
    }

    /**
     * 当前评论取消点赞
     * @param request
     * @return
     */
    @Override
    public int cancelNoteLike(NoteInfo request) {
        System.out.println(list);
        return mapper.cancelNoteLike(request);
    }


    /**
     * 获取当前章节的下一级信息
     *
     * @param request
     * @return
     */
    @Override
    public List<ChapterMenu> nextMenu(RequestDTO request) {
        return mapper.nextKnow(request);
    }

    /**
     * 获取当前课程章节信息
     *
     * @param request
     * @return
     */
    @Override
    public List<ChapterMenu> firstMenu(RequestDTO request) {
        return mapper.firstKnowMenu(request);
    }

    /**
     * 学习下一个知识点
     *
     * @param request
     * @return
     */
    @Override
    public int[] nextKnow(RequestDTO request) {
        // 获取当前课程最后一个学习的知识点
        StudyRecordDTO lastStudy = mapper.getLastStudy(request);
        if (lastStudy == null) {
            // 当前课程未学习，返回第一个知识点
            return new int[0];
        } else {
            // 当前课程已学习，返回下一个知识点
            StudyRecordDTO know = mapper.getNextKnow(lastStudy.getLeftValue(), request.getCourseId());
            int[] result = new int[know.getLevel() - 1];
            for (int i = 0; i < know.getLevel() - 2; i++) {
                result[i] = mapper.getUpLevelId(know.getLeftValue(), know.getRightValue(), i + 2, request.getCourseId());
            }
            result[result.length - 1] = know.getKnowId();
            return result;
        }
    }

    /**
     * 记录章节学习情况
     *
     * @param record
     * @return
     */
    @Override
    public int addStudyRecord(StudyRecordDTO record) {
        // 判断是否为首次插入
        record.setFlag(mapper.isFirstStudy(record.getKnowId(), record.getStudenterId()).isEmpty() ? 1 : 0);
        // 计算学习时间
        record.setUseTime(DateUtils.getUseTime(record.getStartTime(), record.getEndTime()));
        return mapper.insertStudyRecord(record);
    }

    /**
     * 获取学习状态
     *
     * @param studenterId 学生编号
     * @param chapters    章节信息
     * @return
     */
    public List<ChapterMenu> getStudyStatus(String studenterId, List<ChapterMenu> chapters) {
        for (ChapterMenu chapter : chapters) {
            // 获取当前章节下所有知识点内容的数量
            int allNumber = mapper.getKnowContentNumber(chapter);
            // 获取学生当前章节下做完的数量
            int completeNumber = mapper.getCompleteKnow(studenterId, chapter.getCourseId(), chapter.getLeftValue(), chapter.getRightValue());
            if (completeNumber >= allNumber) {
                chapter.setIsStudy(1);
            }
        }
        return chapters;
    }

    /**
     * 获取下一级的章节信息
     *
     * @param request
     * @return
     */
    @Override
    public List<ChapterMenu> KnowList(RequestDTO request) {
        return service.getStudyStatus(request.getStudenterId(), mapper.nextKnow(request));
    }

    /**
     * 根据课程id查询出评论信息
     *
     * @param courseId 课程id
     * @return
     */
    @Override
    public PageInfo<CommentInfo> commentInfo(Integer courseId, String guid, Integer pageNumber, Integer pageSize) {
        // 查询出所有的课程评论信息
        PageHelper.startPage(pageNumber, pageSize);
        List<CommentInfo> comments = mapper.queryComment(courseId);
        for (CommentInfo comment : comments) {
            // 根据评论id查询出评论点赞数
            List<String> studenterIds = mapper.queryLikeInfo(comment.getCommentId());
            comment.setLikes(studenterIds.size());
            // 查询出评论是否已经点赞
            comment.setLikeFlag(studenterIds.contains(guid) ? 1 : 0);
        }
        return new PageInfo<>(comments);
    }


    /**
     * 对评论进行点赞
     *
     * @param commentId 评论id
     * @param guid      学生编号
     * @return 插入的结果 0为失败 其余数字为成功
     */
    @Override
    public int giveLike(Integer commentId, String guid) {
        int result = mapper.giveLike(commentId, guid);
        // 如果插入结果为0代表失败 其他为成功
        if (result == 0) {
            return 0;
        }
        result = mapper.queryLikeInfo(commentId).size();
        return result;
    }

    /**
     * 发表评论
     *
     * @param message 评论内容
     * @param guid    用户编号
     * @return 插入成功返回当前评论的内容
     */
    @Override
    public CommentInfo publishComment(String message, String guid, Integer courseId) {
        int result = mapper.publishComment(message, guid, courseId);
        if (result == 0) {
            // 发布失败
            return null;
        }
        CommentInfo comment = mapper.getLastComment(courseId, guid);
        return comment;
    }

    /**
     * 当前评论取消点赞
     *
     * @param discussId   评论id
     * @param studenterId 学生编号
     * @return 插入的结果 0为失败 其余数字为成功
     */
    @Override
    public int cancelLike(Integer discussId, String studenterId) {
        return mapper.cancelLike(discussId, studenterId);
    }


    /**
     * 答题模块题目内容
     *
     * @param knowId   知识点id
     * @return
     */
    @Override
    public List<ResultQuestion> knowQuestion(Integer knowId) {
        // 随机获取2-5道题目
        int count = new Random().nextInt(4) + 2;
        // 获取当前知识点下的题目
        List<Integer> ids = new ArrayList<>();
        // 判断今天是否属于规定出新题的日期
        if(list.contains(DateUtils.getWeekNumber())){
            ids = mapper.getRuleQuestionId(knowId,count);
        }else{
            // 如果不是规定的星期，则随机拿题目
            ids = mapper.getQuestionId(knowId,count);
        }
        // 如果取出的数量<随机的题目数 则拿随机的题目
        if(ids.size()<count){
            ids.addAll(mapper.getRandom(knowId,(count-ids.size())));
        }
        return SwitchQuestionUtils.switchQuestion(mapper.getQuestionInfo(ids,knowId,mapper.getKnowName(knowId)));
    }

    /**
     * 收藏当前知识点
     *
     * @param chapterId   章节id
     * @param courseId    课程id
     * @param studenterId 学生编号
     * @param knowId      知识点id
     * @param courseName  课程名称
     * @param subjectId   科目id
     * @return
     */
    @Override
    public int collectKnow(Integer courseId, Integer chapterId, String studenterId, Integer knowId, String courseName, Integer subjectId) {
        return mapper.collectKnow(courseId, chapterId, courseName, subjectId, studenterId, knowId);
    }

    /**
     * 提交学生答题情况
     *
     * @param request
     * @return
     */
    @Override
    public int submitQuestion(List<Correct> request) {
        List<Correct> result = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Correct correct : request) {
            try {
                long a = simpleDateFormat.parse(correct.getStartdt()).getTime();
                long b = simpleDateFormat.parse(correct.getEnddt()).getTime();
                long c = (b - a);
                correct.setUseTime(c);
            } catch (Exception e) {
            }
            // 判断题目是否为多选题
            if ("2".equals(correct.getQuestionType())) {
                // 先判断长度是否相同
                if (!(correct.getAnswer().length() == correct.getFillAnswer().length())) {
                    // 长度不相同一定错误
                    correct.setCorrect(0);
                } else {
                    char[] fillAnswer = correct.getFillAnswer().toCharArray();
                    char[] answer = correct.getAnswer().toCharArray();
                    // 长度相同
                    // 对数组进行排序
                    Arrays.sort(answer);
                    Arrays.sort(fillAnswer);
                    // 将答案按照大小排序
                    // 比较选择和答案是否相等
                    correct.setCorrect(String.valueOf(answer).equals(String.valueOf(fillAnswer)) ? 1 : 0);
                }
            } else {
                // 判断题目是否正确
                correct.setCorrect(correct.getFillAnswer().equals(correct.getAnswer()) ? 1 : 0);
            }
            //  if(correct.getSendType()==""||correct.getSendType()==null) {  //学习课堂基本提交
            if (correct.getCorrect() == 0) {
                // 题目错误插入错题库
                mapper.insertWrongQuestion(correct);
            }
            // 查询当前题目是否已经有难度
            String questionId = correct.getQuestionId();
            int level = mapper.getQuestionLevel(questionId);
            if(level==0) {
                // 查询当前题目的数量是否达到了99
                QuestionCorrect number = mapper.getQuestionNumber(questionId);
                if (number.getCount() >= 99) {
                    int count = number.getCount() + 1;
                    int errCount = correct.getCorrect() == 0 ? number.getErrCount() + 1 : number.getErrCount();
                    // 则开始计算正确率
                    mapper.updateQuestion(questionId, DecimalUtils.calculationLevel(count, errCount));
                }
            }
            //4.进行推送记录插入
            //4.1  查询是否第一次记录此推送记录  1.表示第一次推送   >1表示多次
            if (correct.getPlushFrequency()<=1) {
                /**
                 *查詢是否有相同知識點推送
                 */
                int selectplush = mapper.selectplush(correct.getKnowId(),correct.getStudenterId());
                if (selectplush == 0) {
                    /**
                     *
                     * 查询是否是未关联的题目  查询selectpushquestionby >0 已经关联  <0未关联
                     */
                    // int selectpushquestionby = mapper.selectpushquestionby(correct);  暂时去掉 没有数据
                    //if (selectpushquestionby > 0) {
                    Float a = (float) 0.0;
                    Long aLong = TimeCalculate.calculateNext((correct.getUseTime().intValue()), null, a, 1);
                    Long millisecond = Instant.now().toEpochMilli();
                    Long  miao=aLong+millisecond;
                    String planTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(miao));
                    //                    String planTime = DateUtils.addTime(TimeCalculate.calculateNext((correct.getUseTime().intValue()), null, a, 1));
//                             System.out.println("下次推送时间"+planTime);
                    System.out.println("参数1:"+aLong);
                    System.out.println("参数2:"+millisecond);
                    System.out.println(
                            "参数3:"+miao);
                    System.out.println("参数4:"+planTime);

                    mapper.insertPushQuestion(correct.getCourseId(), correct.getStudenterId(), planTime, correct.getKnowId(), correct.getQuestionType(), 1);
                    //} else {
                    // System.out.println("题目是未关联的题目,暂不考虑----过滤掉");
                    //}
                }else{
                    System.out.println("已有相同知識點");
                }
            } else {
                Integer updatepushstatic = 0;
                if (correct.getPlushFrequency() > 1) {
                    updatepushstatic = mapper.updatepushstatic(correct);
                }

                // 将当前知识点内容插入到推送表，对学生进行想关推送
                int selectplush = mapper.selectplush(correct.getKnowId(),correct.getStudenterId()); //查询是否有相同知识点推送记录
                if (selectplush == 0) {
                    if (updatepushstatic  == 1) {
                        if (correct.getPlushFrequency() <= 6) {
                            int selectpushquestionby = mapper.selectpushquestionby(correct);
                            if (selectpushquestionby >= 1) {
                                if ("1".equals(correct.getSendType())) {
                                    String s = mapper.SelectStudyTimeByknowledge(correct);
                                    if ("".equals(s) || s == null) {
                                        s = "1";
                                    }
                                    Long aLong =TimeCalculate.calculateNext(((correct.getUseTime().intValue())), Integer.parseInt(s), (float) correct.getCorrect(), correct.getPlushFrequency());
                                    Long millisecond = Instant.now().toEpochMilli();
                                    Long  miao=aLong+millisecond;
                                    String planTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(miao));
                                    System.out.println("参数1:"+aLong);
                                    System.out.println("参数2:"+millisecond);
                                    System.out.println("参数3:"+miao);
                                    System.out.println("参数4:"+planTime);
                                    System.out.println("下次推送时间"+planTime);
                                    mapper.insertPushQuestionbyKnowledge( correct.getCourseId(), correct.getStudenterId(), planTime, correct.getKnowId(), correct.getQuestionType(), correct.getPlushFrequency(), correct.getSendType());
                                } else if ("6".equals(correct.getSendType())) {
                                    /**
                                     * 1查詢上次学习时长
                                     * 2.推送次数
                                     */
                                    String s = mapper.SelectStudyTimeBy(correct);
                                    // System.out.println(s);
                                    if ("".equals(s) || s == null) {
                                        s = "1";
                                    }

                                    Long aLong =TimeCalculate.calculateNext(((correct.getUseTime().intValue())), Integer.parseInt(s), (float) correct.getCorrect(), correct.getPlushFrequency());
                                    Long millisecond = Instant.now().toEpochMilli();
                                    Long  miao=aLong+millisecond;
                                    String planTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(miao));
                                    System.out.println("参数1:"+aLong);
                                    System.out.println("参数2:"+millisecond);
                                    System.out.println("参数3:"+miao);
                                    System.out.println("参数4:"+planTime);
                                    System.out.println("下次推送时间"+planTime);
                                    mapper.insertPushQuestionbyKnowledge(correct.getCourseId(), correct.getStudenterId(), planTime, correct.getKnowId(), correct.getQuestionType(), correct.getPlushFrequency(), correct.getSendType());

                                }

                            } else {
                                System.out.println("题目是未关联的题目,暂不考虑----过滤掉");
                            }

                        } else {
                            System.out.println("推送已经7次,不进入推送列表");
                        }
                    }else{
                        System.out.println("上次推送状态修改失败.....");
                    }

                } else {
                    System.out.println("已有推送知识点内容...");
                }
            }
            // 算出学生做题时间
            correct.setUseTime(DateUtils.getUseTime(correct));
            // 判断是否有疑问
            if (correct.getUseTime() < 3) {
                // 当前题目不属于认真答题，不记录
                correct.setIsQuery(0);
            } else if (correct.getUseTime() > 3 && correct.getUseTime() < 120) {
                // 当前题目属于正常答题
                correct.setIsQuery(1);
                result.add(correct);
            } else if (correct.getUseTime() > 120 && correct.getUseTime() < 600) {
                // 学生学习时间过长，有疑问就将当前题目插入难题表和推送提醒表
                correct.setIsQuery(2);
                result.add(correct);
                // 插入难题库
                mapper.insertDifficultyQuestion(correct);
            } else {
                // 其余属于不认真答题，不记录
                correct.setIsQuery(3);
            }
        }
        if (result.isEmpty()) {
            return 1;
        }
        return mapper.submitQuestion(result);
    }
}
