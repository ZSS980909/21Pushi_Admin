package com.ershiyi.service;

import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zss98
 * 学生个人中心页面操作服务层
 * @date 2020.07.28 09:21
 */
public interface PersonalCenterService {
    /**
     * @Description: 根据学生当前的编号查询学生个人中心详细信息 
     * @Param:  studenterId 学生编号
     * @return:  学生信息实体类
     * @Date: 2020/7/28 
     *
     */
    public StudentInformation findByStudenterId(String studenterId);


     /**
      * @Description: 修改用户头像 
      * @Param:  guid 学生id
      * @Param： url 图片链接
      * @return:  修改结果 0为失败，其余为成功
      * @Date: 2020/7/28 
      *
      */
     public int UpdateImage(String guid,String url);

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
     public int UpdateInfo(String guid, String name, String sex, String birthday);

    /**
     * 根据学生编号查询出学生所有的课程数量
     * @param studenterId
     * @return
     */
     public int queryCourseNumber(String studenterId);

     /**
      * @Description: 查询学生所有收藏的课程信息 
      * @Param: studenterId 学生编号 
      * @Param: pageNumber 当前的页码
      * @Param: sizeNumber 每页展示的数量
      * @return: 课程详细信息集合 
      * @Date: 2020/7/29 
      *
      */
     public PageInfo<CoursePojo> getCollectCourse(String studenterId, int pageNumber, int sizeNumber);

    /**
     * 获取所有的课程编号
     * @return
     */
     public List<subjectInfo> getAllSubject();

    /**
     * 根据学生编号和学科编号查询到所有的知识点
     * @param studenterId 学生编号
     * @param subjectId 学科id 0表示查询所有的学科
     * @param pageNumber 页码
     * @param pageSize 每页的数量
     * @return
     */
     public PageInfo<KnowLedges> findCollectKnow(String studenterId,int subjectId,int pageNumber,int pageSize);
    /**
     * 根据学生编号和学科编号查询到学生所有的学习记录
     * @param studenterId 学生编号
     * @param subjectId 学科id 0表示查询所有的学科
     * @param pageNumber 页码
     * @param pageSize 每页的数量
     * @return
     */
     public PageInfo<IdeaRecord> findIdeaRecord(String studenterId, int subjectId, int pageNumber, int pageSize);

     /**
     * 根据id删除对应的课程
     * @param ids id集合
     * @return
     */
     public int deleteCourse(List<Integer> ids,String studenterId);
    /**
     * 根据id删除对应的课程
     * @param ids id集合
     * @return
     */
     public int deleteKnowledge(List<Integer> ids);
    /**
     * 根据id删除对应的课程
     * @param ids id集合
     * @return
     */
     public int deleteIdea(List<Integer> ids);
    /**
     * @Description: 查询学生所有浏览过的课程信息 
     * @Param: studenterId 学生编号 
     * @Param: pageNumber 当前的页码
     * @Param: sizeNumber 每页展示的数量
     * @return: 课程详细信息集合 
     * @Date: 2020/7/29 
     */
    public PageInfo<CoursePojo> findHistoryCourse(String studenterId, int pageSize, int sizeNumber);

    /**
     *  根据id删除历史的课程记录
     *  @param studenterId 学生编号
     *  @param courseIds 课程id集合
     *  @return 操作结果 0代表失败，其余成功
     */
    public int deleteHistoryCourse(List<Integer> courseIds,String studenterId);

    /**
     * 根据学生编号查询出收到的消息
     * @param studenterId 学生编号
     * @param pageNumber  页码
     * @param pageSize    每页展示的数量
     * @return
     */
    public PageInfo<MessageInfo> queryMessage(String studenterId,int pageNumber,int pageSize);

    /**
     * 查询已完成的课程
     * @param studenterId 学生编号
     * @param pageNumber 页码
     * @param pageSize    每页展示的数量
     * @return
     */
    public PageInfo<CoursePojo> queryFinish(String studenterId,int pageNumber,int pageSize);

    /**
     * 修改用户密码
     * @param guid 用户id
     * @param passWord 用户原密码
     * @param newPass 用户新密码
     * @return
     */
    Integer modifyPass(String guid, String passWord, String newPass);

    /**
     * 根据手机号查询信息
     * @param mobilePhone
     * @return
     */
    List<String> queryInfoByPhone(String mobilePhone);

    /**
     * 修改手机号
     * @param request
     * @return
     */
    int modifyPhone(RequestDTO request);

    /**
     * 用户意见反馈
     * @param feedback
     * @return
     */
    Integer submitOpinion(A_Feedback feedback);

    /**
     * 检查版本更新
     * @param request
     * @return
     */
    LinkedHashMap checkUpdate(RequestDTO request);


    List<CoursePojo> myCourse(RequestDTO requestdto);

    List<String> checkPhone(RequestDTO request);

    /**
     * 获取二维码地址
     * @param request
     * @return
     */
    String getQRCode(RequestDTO request);

    /**
     * 获取最新的app下载地址
     * @param request
     * @return
     */
    List<HashMap> getDownUrl(RequestDTO request);

    String getPassword(RequestDTO request);
}
