package com.ershiyi.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.ershiyi.Utils.DateUtils;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.mapper.PersonalCenterMapper;
import com.ershiyi.mapper.StudyDataMapper;
import com.ershiyi.service.PersonalCenterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 学生个人中心操作服务层实现类
 * @author: zss98
 * @date: 2020-07-28 09:59
 * @version: 1.0
 */
@Service
public class PersonalCenterServiceImpl implements PersonalCenterService {

    private static String PREFIX="https://api.pwmqr.com/qrcode/create/?url=";

    @Autowired
    private PersonalCenterMapper mapper;

    @Autowired
    private StudyDataMapper studyDataMapper;

    /**
     * @Description: 根据学生当前的编号查询学生个人中心详细信息 
     * @Param:  studenterId 学生编号
     * @return:  学生信息实体类
     * @Date: 2020/7/28 
     *
     */
    @Override
    public StudentInformation findByStudenterId(String studenterId) {
        return mapper.findByStudenterId(studenterId);
    }
    /**
     * @Description: 修改用户头像 
     * @Param:  guid 学生编号
     * @Param： url 图片链接
     * @return:  修改结果 0为失败，其余为成功
     * @Date: 2020/7/28 
     *
     */
    @Override
    public int UpdateImage(String guid, String url) {
        return mapper.updateImage(guid,url);
    }

    /**
     * @Description: 修改个人信息 
     * @Param:  guid 学生id
     * @Param:  name 修改后的昵称
     * @Param:  sex 修改后的性别
     * @Param:  birthday 修改后的生日
     * @return:  修改结果 0为失败，其余为成功
     * @Date: 2020/7/28 
     *
     */
    @Override
    public int UpdateInfo(String guid, String name, String sex, String birthday) {
        return mapper.updateInfo(guid,name,sex,birthday);
    }
    /**
     * 根据学生编号查询出学生所有的课程数量
     * @param studenterId
     * @return
     */
    @Override
    public int queryCourseNumber(String studenterId) {
        return mapper.queryCourseNumber(studenterId);
    }

    /**
     * @Description: 查询学生所有收藏的课程信息 
     * @Param: studenterId 学生编号 
     * @Param: pageNumber 当前的页码
     * @Param: sizeNumber 每页展示的数量
     * @return: 课程详细信息集合 
     * @Date: 2020/7/29 
     *
     */
    @Override
    public PageInfo<CoursePojo> getCollectCourse(String studenterId, int pageNumber, int sizeNumber) {
        // 开启分页
        PageHelper.startPage(pageNumber,sizeNumber);
        return new PageInfo(mapper.getCollectCourse(studenterId));
    }

    /**
     * 返回导航栏所有科目
     * @return
     */
    @Override
    public List<subjectInfo> getAllSubject() {
        List<subjectInfo> subjects = new ArrayList<>();
        subjects.add(new subjectInfo(0,"全部"));
        subjects.addAll(studyDataMapper.findAllSubject());
        return subjects;
    }
    /**
     * 根据学生编号和学科编号查询到所有的知识点
     * @param studenterId 学生编号
     * @param subjectId 学科id 0表示查询所有的学科
     * @param pageNumber 页码
     * @param pageSize 每页的数量
     * @return
     */
    @Override
    public PageInfo<KnowLedges> findCollectKnow(String studenterId, int subjectId,int pageNumber,int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNumber,pageSize);
        List<KnowLedges> results = mapper.findAllKnowledge(studenterId, subjectId);
        return new PageInfo<>(results);
    }

    /**
     * 根据学生编号和科目查询到学生所有的学习记录
     * @param studenterId 学生编号
     * @param subjectId 学科id 0表示查询所有的学科
     * @param pageNumber 页码
     * @param pageSize 每页的数量
     * @return
     */
    @Override
    public PageInfo<IdeaRecord> findIdeaRecord(String studenterId, int subjectId, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<IdeaRecord> results = mapper.findIdeaRecord(studenterId, subjectId);
        return new PageInfo<>(results);
    }
    /**
     * 根据id删除对应的课程
     * @param ids id集合
     * @return
     */
    @Override
    public int deleteCourse(List<Integer> ids,String studenterId) {
        int result = 0;
        for (Integer id : ids) {
            result = mapper.deleteCourse(id,studenterId);
        }
        return result;
    }
    /**
     * 根据id删除对应的知识点
     * @param ids id集合
     * @return
     */
    @Override
    public int deleteKnowledge(List<Integer> ids) {
        int result = 0;
        for (Integer id : ids) {
            result = mapper.deleteKnowledge(id);
        }
        return result;
    }
    /**
     * 根据id删除对应的学习记录
     * @param ids id集合
     * @return
     */
    @Override
    public int deleteIdea(List<Integer> ids) {
        int result = 0;
        for (Integer id : ids) {
            result = mapper.deleteIdea(id);
        }
        return result;
    }

    @Override
    public PageInfo<CoursePojo> findHistoryCourse(String studenterId, int pageNumber, int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNumber,pageSize);
        // 根据学生编号获取到学生所有浏览过的课程
        List<Integer> courseId = mapper.findHistoryCourse(studenterId);
        if(courseId.isEmpty()){
            return new PageInfo<>(new ArrayList<>());
        }
        List<CoursePojo> results = mapper.getCourseInfo(courseId,studenterId);
        return new PageInfo(results);
    }

    @Override
    public int deleteHistoryCourse(List<Integer> courseIds,String studenterId) {
        int result = 0;
        for (Integer id : courseIds) {
            result = mapper.deleteHistoryCourse(studenterId,id);
        }
        return result;
    }


    @Override
    public PageInfo<MessageInfo> queryMessage(String studenterId,int pageNumber,int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNumber,pageSize);
        // 查询出学生所有收到的信息
        List<MessageInfo> messageInfos = mapper.queryMessageInfo(studenterId);
        // 查询出接受者的信息
        MessageUserInfo acceptInfo = mapper.queryUserInfo(studenterId);
        for (MessageInfo messageInfo : messageInfos) {
            // 将接受者信息存入到集合中
            messageInfo.setAcceptName(acceptInfo.getName());
            messageInfo.setAcceptImageUrl(acceptInfo.getImageUrl());
            // 判断学生信息发送者类型来调用不同的查询查询用户相信信息
            // 0为学生 1为老师 2为家长 3为校长
            if(messageInfo.getUserType()==0){
                // 将发送者的信息查询出来，存入到信息集合中
                MessageUserInfo messageUserInfo = mapper.queryUserInfo(messageInfo.getSendId());
                messageInfo.setGuid(messageUserInfo.getGuid());
                messageInfo.setSendName(messageUserInfo.getName());
                messageInfo.setSendImageUrl(messageUserInfo.getImageUrl());
            }
        }
        return new PageInfo<>(messageInfos);
    }

    /**
     * 查询已完成的课程
     * @param studenterId 学生编号
     * @param pageNumber  页码
     * @param pageSize    每页展示的数量
     * @return
     */
    @Override
    public PageInfo<CoursePojo> queryFinish(String studenterId, int pageNumber, int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNumber,pageSize);
        List<CoursePojo> results = mapper.queryFinishCourse(studenterId);
        return new PageInfo<>(results);
    }

    /**
     * 修改用户密码
     * @param guid 用户id
     * @param passWord 用户原密码
     * @param newPass 用户新密码
     * @return
     */
    @Override
    public Integer modifyPass(String guid, String passWord, String newPass) {
        // 获取用户原密码
        String oldPass = mapper.getUserPass(guid);
        // 加密用户的密码
        passWord = SecureUtil.md5(passWord);
        if(oldPass==null||newPass==null||passWord==null){
            return 202;
        }
        if(!StringUtils.endsWithIgnoreCase(oldPass,passWord)){
            return 201;
        }
        newPass = SecureUtil.md5(newPass);
        int result = mapper.modifyPass(guid,newPass);
        if(result==0){
            // 修改失败
            return 202;
        }
        return 200;
    }


    @Override
    public List<String> queryInfoByPhone(String mobilePhone) {
        return mapper.queryInfoByPhone(mobilePhone);
    }

    @Override
    public int modifyPhone(RequestDTO request) {
        return mapper.modifyPhone(request);
    }

    /**
     * 提交用户意见
     * @param feedback
     * @return
     */
    @Override
    public Integer submitOpinion(A_Feedback feedback) {
        return mapper.submitOpinion(feedback);
    }

    /**
     * app软件更新
     * @param request
     * @return
     */
    @Override
    public LinkedHashMap checkUpdate(RequestDTO request) {
        // 从数据库获取最新的版本信息
        ApplicationVersion version = mapper.checkUpdate(request);
        // 比较数据库最新的版本是否比最新的大 是就返回大于0
        LinkedHashMap map = new LinkedHashMap();
        if(version==null){
            map.put("status",0);
            map.put("size","0.0");
            map.put("version",request.getVersion());
            map.put("updateInfo","");
            map.put("data","");
            return map;
        }
        int result = version.getVersion().compareTo(request.getVersion());
        if(result>0){
            // 如果大于0,代表有新的版本可以用
            map.put("status",1);
            if(version.getUrgentUpdate()==1){
                // 如果等于1代表这个版本必须更新
                map.put("status",2);
            }
            map.put("size",version.getSize());
            map.put("version",version.getVersion());
            map.put("updateInfo",version.getMessage());
            map.put("data",version.getDownUrl());
        }
        return map;
    }

    @Override
    public List<CoursePojo> myCourse(RequestDTO requestdto) {
        PageHelper.startPage(requestdto.getPageNumber(),requestdto.getPageSize());
        List<CoursePojo> results =  mapper.myCourse(requestdto);
        return results;
    }

    @Override
    public List<String> checkPhone(RequestDTO request) {
        return mapper.checkPhone(request);
    }

    /**
     * 获取二维码
     * @param request
     * @return
     */
    @Override
    public String getQRCode(RequestDTO request) {
        // 获取当前用户是否存在验证码
        String images = mapper.getQRCode(request);
        if(images==null||images.isEmpty()||images.equals("null")){
            // 不存在，则生成新的地址到数据库
            String url = PREFIX+"phone:"+request.getLoginId();
            mapper.insertQRCode(request.getLoginId(),url);
            return url;
        }
        return images;
    }

    @Override
    public String getPassword(RequestDTO request) {
        return mapper.getPassword(request);
    }

    /**
     * 获取app下载地址
     * @param request
     * @return
     */
    @Override
    public List<HashMap> getDownUrl(RequestDTO request) {
        List<HashMap> results = new ArrayList<>();
        List<ApplicationVersion> downUrl = mapper.getDownUrl();
        if(downUrl.isEmpty()){
            // 集合为空
            return null;
        }
        for (ApplicationVersion applicationVersion : downUrl) {
            HashMap<String, String> map = new HashMap<>();
            map.put("appType",applicationVersion.getAppType());
            map.put("downUrl",PREFIX+applicationVersion.getDownUrl());
            map.put("size",applicationVersion.getSize());
            results.add(map);
        }
        return results;
    }

}
