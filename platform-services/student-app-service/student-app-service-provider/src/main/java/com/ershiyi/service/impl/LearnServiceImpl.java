package com.ershiyi.service.impl;

import com.ershiyi.Utils.DateUtils;
import com.ershiyi.Utils.IdsUtils;
import com.ershiyi.Utils.SwitchQuestionUtils;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.mapper.LearnMapper;
import com.ershiyi.mapper.PersonalCenterMapper;
import com.ershiyi.service.LearnService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twenty.alp.TimeCalculate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 学科课堂服务层实现类
 * @author: zss98
 * @date: 2020-08-05 11:40
 * @version: 1.0
 */
@Service
public class LearnServiceImpl<T> implements LearnService {

    @Resource
    private LearnMapper mapper;

    /**
     * 根据学生编号查询学生课程列表
     * @param studenterId 学生编号
     * @param pageNumber 页码
     * @param pageSize 每页展示的数量
     * @return
     */
    @Override
    public PageInfo<CoursePojo> courseList(String studenterId, Integer pageNumber, Integer pageSize) {
        // 开启分页
        PageHelper.startPage(pageNumber,pageSize);
        // 根据学生编号查询出学生所有的课程id集合
        List<CoursePojo> results = mapper.findAllCourse(studenterId);
        return new PageInfo<>(results);
    }



    @Override
    public List<ChapterMenu> chapterMenu(RequestDTO request) {
        // 将学生浏览该课程存入到历史记录中
        mapper.addHistory(request.getStudenterId(), request.getCourseId());
        // 查询出学生所有学习过的章节id
        List<Integer> studyChapter = mapper.getStudyChapter(request);
        List<ChapterMenu> results = mapper.knowledgeMenu(request);
        for (ChapterMenu result : results) {
            for (Integer chapterId : studyChapter) {
                if(chapterId==result.getChapterId()){
                    result.setIsStudy(1);
                }
            }
        }
        return results;
    }

    @Override
    public List<ChapterMenu> KnowList(RequestDTO request) {
        return mapper.nextKnow(request);
    }

    /**
     * 根据课程id查询出评论信息
     * @param courseId 课程id
     * @return
     */
    @Override
    public PageInfo<CommentInfo> commentInfo(Integer courseId, String guid, Integer pageNumber, Integer pageSize) {
        // 查询出所有的课程评论信息
        PageHelper.startPage(pageNumber,pageSize);
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
     * @param commentId 评论id
     * @param guid 学生编号
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
     * @param message 评论内容
     * @param guid 用户编号
     * @return 插入成功返回当前评论的内容
     */
    @Override
    public CommentInfo publishComment(String message, String guid, Integer courseId) {
        int result = mapper.publishComment(message, guid, courseId);
        if(result==0){
            // 发布失败
            return null;
        }
        CommentInfo comment = mapper.getLastComment(courseId, guid);
        return comment;
    }

    /**
     * 当前评论取消点赞
     * @param discussId 评论id
     * @param studenterId 学生编号
     * @return 插入的结果 0为失败 其余数字为成功
     */
    @Override
    public int cancelLike(Integer discussId, String studenterId) {
        return mapper.cancelLike(discussId, studenterId);
    }

    /**
     * 答题模块题目内容
     * @param knowId 知识点id
     * @param courseId  课程id
     * @return
     */
    @Override
    public List<ResultQuestion> knowQuestion(Integer knowId,int courseId) {
        // 随机获取2-5道题目
        int count = new Random().nextInt(4)+2;
        List<ResultQuestion> results = new ArrayList<>();
        // 获取当前知识点下的题目
        List<QuestionType> ids = mapper.getQuestionId(knowId,count);
        if(ids.size()<count){
            // 如果题目不够就随机出两道当前科目的题目
            results.addAll(SwitchQuestionUtils.choiceQuestion(mapper.getRandom(courseId,(count-ids.size()))));
        }
        for (QuestionType id : ids) {
            if(id.getQuestionType()==1){
                results.add(SwitchQuestionUtils.choiceQuestion(mapper.choiceSQuestion(id)));
            }else if(id.getQuestionType()==2){
                results.add(SwitchQuestionUtils.choiceQuestion(mapper.choiceMQuestion(id)));
            }else if(id.getQuestionType()==3){
                results.add(SwitchQuestionUtils.judgeQuestion(mapper.judgeQuestion(id)));
            }
        }
        return results;
    }

    /**
     * 收藏当前知识点
     * @param chapterId  章节id
     * @param courseId  课程id
     * @param studenterId 学生编号
     * @param knowId    知识点id
     * @param courseName 课程名称
     * @param subjectId 科目id
     * @return
     */
    @Override
    public int collectKnow(Integer courseId,Integer chapterId, String studenterId, Integer knowId, String courseName, Integer subjectId) {
        return mapper.collectKnow(courseId,chapterId,courseName,subjectId,studenterId,knowId);
    }

    /**
     * 提交学生答题情况
     * @param request
     * @return
     */
    @Override
    public int submitQuestion(List<Correct> request) {

        List<Correct> result = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Correct correct : request) {
            try{
                long a = simpleDateFormat.parse(correct.getStartdt()).getTime();
                long b = simpleDateFormat.parse(correct.getEnddt()).getTime();
                long  c = (b - a);
                correct.setUseTime(c);
            }catch (Exception e){

            }
            // 判断题目是否为多选题
            if("2".equals(correct.getQuestionType())){
                // 先判断长度是否相同
                if(!(correct.getAnswer().length()==correct.getFillAnswer().length())){
                    // 长度不相同一定错误
                    correct.setCorrect(0);
                }else{
                    char[]  fillAnswer = correct.getFillAnswer().toCharArray();
                    char[]  answer = correct.getAnswer().toCharArray();
                    // 长度相同
                    // 对数组进行排序
                    Arrays.sort(answer);
                    Arrays.sort(fillAnswer);
                    // 将答案按照大小排序
                    // 比较选择和答案是否相等
                    correct.setCorrect(String.valueOf(answer).equals(String.valueOf(fillAnswer)) ? 1:0);
                }
            }else {
                // 判断题目是否正确
                correct.setCorrect(correct.getFillAnswer().equals(correct.getAnswer()) ? 1 : 0);
            }
          //  if(correct.getSendType()==""||correct.getSendType()==null) {  //学习课堂基本提交
            if (correct.getCorrect() == 0) {
                // 题目错误插入错题库
                mapper.insertWrongQuestion(correct);
            }
            if (correct.getSendType()==null||correct.getSendType()==""){
                //第一次推送
                /**
                 * 查询是否是未关联的题目
                 */
                int selectpushquestionby = mapper.selectpushquestionby(correct);
                if (selectpushquestionby > 0) {
                    Float a = (float)0.0;
/*
                    String planTime = DateUtils.addTime(TimeCalculate.calculateNext(correct.getUseTime().intValue(),0,a,1));
                    mapper.insertPushQuestion(correct.getChapterId(), correct.getCourseId(), correct.getStudenterId(), planTime, correct.getKnowContentId(), correct.getQuestionType(), 1);
*/
                } else {
                    System.out.println("题目是未关联的题目,暂不考虑----过滤掉");
                }
            }else {
                Integer updatepushstatic=0;
                if (correct.getPlushFrequency() >= 1) {
                    //多次推送
                    if(correct.getKnowContentId().contains(",")){
                        String[] split = correct.getKnowContentId().split(",");
                        correct.setKnowContentId(split[1]);
                    }
                     updatepushstatic = mapper.updatepushstatic(correct);
                    System.out.println("推送知识点次数更改成功,次数" + correct.getPlushFrequency());
                }

                // 将当前知识点内容插入到推送表，对学生进行想关推送
                int selectplush = mapper.selectplush(correct); //查询是否有相同知识点推送记录
                if(selectplush==0) {
                        if (updatepushstatic == 1) {
                            if (correct.getPlushFrequency() <= 6) {
                                int selectpushquestionby = mapper.selectpushquestionby(correct);
                                if (selectpushquestionby == 1) {
                                    if ("1".equals(correct.getSendType())) {
                                        String s = mapper.SelectStudyTimeByknowledge(correct);
/*
                                        String planTime = DateUtils.addTime(TimeCalculate.calculateNext(Integer.parseInt(s), correct.getUseTime().intValue(), (float) correct.getCorrect(), correct.getPlushFrequency()));
                                        mapper.insertPushQuestionbyKnowledge(correct.getChapterId(), correct.getCourseId(), correct.getStudenterId(), planTime, correct.getKnowContentId(), correct.getQuestionType(), correct.getPlushFrequency() + 1, correct.getSendType());
*/
                                    } else if ("6".equals(correct.getSendType())) {
                                        /**
                                         * 1查詢上次学习时长
                                         * 2.推送次数
                                         */
                                        String s = mapper.SelectStudyTimeBy(correct);
/*
                                        String planTime = DateUtils.addTime(TimeCalculate.calculateNext(Integer.parseInt(s), correct.getUseTime().intValue(), (float) correct.getCorrect(), correct.getPlushFrequency()));
                                        mapper.insertPushQuestionbyKnowledge(correct.getChapterId(), correct.getCourseId(), correct.getStudenterId(), planTime, correct.getKnowContentId(), correct.getQuestionType(), correct.getPlushFrequency() + 1, correct.getSendType());
*/

                                    }

                                } else {
                                    System.out.println("题目是未关联的题目,暂不考虑----过滤掉");
                                }

                            } else {
                                System.out.println("推送已经7次,不进入推送列表");
                            }
                        }

                }else{
                    System.out.println("已有推送知识点内容...");
                }
            }
            // 算出学生做题时间
            correct.setUseTime(DateUtils.getUseTime(correct));
            // 判断是否有疑问
            if(correct.getUseTime()<3){
                // 当前题目不属于认真答题，不记录
                correct.setIsQuery(0);
            }else if(correct.getUseTime()>3&&correct.getUseTime()<120){
                // 当前题目属于正常答题
                correct.setIsQuery(1);
                result.add(correct);
            }else if(correct.getUseTime()>120&&correct.getUseTime()<600){
                // 学生学习时间过长，有疑问就将当前题目插入难题表和推送提醒表
                correct.setIsQuery(2);
                result.add(correct);
                // 插入难题库
                mapper.insertDifficultyQuestion(correct);
            }else{
                // 其余属于不认真答题，不记录
                correct.setIsQuery(3);
            }
        }
        if(result.isEmpty()){
            return 1;
        }
        return mapper.submitQuestion(result);
    }

}
