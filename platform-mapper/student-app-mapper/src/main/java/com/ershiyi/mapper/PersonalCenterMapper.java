package com.ershiyi.mapper;

import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Description: 学生个人中心操作持久层接口
 * @author: zss98
 * @date: 2020-07-28 10:02
 * @version: 1.0
 */
@Mapper
@Repository
public interface PersonalCenterMapper {
    /**
     * @Description: 查询学生的详细个人信息 
     * @Param: studentId 学生编号 
     * @return:  学生信息实体类
     * @Date: 2020/7/28 
     *
     */
    @Select("select * from common_student_info where studenterId = #{studentId}")
    public StudentInformation findByStudenterId(@Param("studentId") String studentId);

    /**
     * @Description: 修改用户头像
     * @param guid 学生编号
     * @param url 头像图片链接
     * @Date: 2020/7/28 
     * @return 上传结果
     */
    @Update("update sys_user set userimage = #{url} where guid = #{guid}")
    public int updateImage(@Param("guid")String guid, @Param("url")String url);

    /**
     * 根据学生编号查询出学生课程总数
     * @param studentId 学生编号
     * @return
     */
    @Select("select count(*) from common_course_purchase where studenterId = #{studentId} and status = 1")
    public int queryCourseNumber(@Param("studentId")String studentId);

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
    public int updateInfo(@Param("guid")String guid, @Param("name")String name,@Param("sex")String sex,@Param("birthday")String birthday);

    /**
     * 根据学生编号查询出学生所有收藏的课程id
     * @param studentId 学生编号
     * @return
     */
    @Select("select * from collect_course_info where studenterId = #{studentId} order by id desc")
    public List<CoursePojo> findCollectCourse(@Param("studentId")String studentId);


    /**
     * 根据学生编号和科目查询出学生所有收藏的知识点
     * @param studentId 学生编号
     * @param subjectId 科目id 0代表查询全部
     * @return
     */
    @Select({"<script>" +
            "select * from like_knowledge where studenterId = #{studentId}"+
            "<if test='subjectId!=0'>and subjectId = #{subjectId} </if> order by createdt desc " +
            "</script>"})
    public List<KnowLedges> findAllKnowledge(@Param("studentId")String studentId,@Param("subjectId")int subjectId);

    /**
     * 根据学生编号和科目查询出学生所有收藏的学习记录
     * @param studentId 学生编号
     * @param subjectId 科目id 0代表查询全部
     * @return
     */
    @Select({"<script>" +
            "select " +
            "subjectid,id as ideaId,idea,date_format(createdt,'%Y-%m-%d %H:%i:%s') as recordTime" +
            " from common_collect_record where studenterId = #{studentId}"+
            "<if test='subjectId!=0'>and subjectId = #{subjectId} </if> "  +
            "and deleted = 0 order by createdt desc" +
            "</script>"})
    public List<IdeaRecord> findIdeaRecord(@Param("studentId")String studentId,@Param("subjectId")int subjectId);

    /**
     * 根据课程id删除收藏中所有对应的课程
     * @param courseId 课程id
     * @param studentId 学生编号
     * @return
     */
    @Update("update common_collect_course set deleted = 1 where courseId = #{courseId} and studenterId = #{studentId}")
    public int deleteCourse(@Param("courseId") int courseId,@Param("studentId") String studentId);

    /**
     * 根据课程id删除收藏中所有对应的知识点
     * @param id 知识点id
     * @return
     */
    @Update("update common_collect_knowledge set deleted = 1 where id = #{id}")
    public int deleteKnowledge(@Param("id") int id);

    /**
     * 根据课程id删除收藏中所有对应的学习记录
     * @param id 学习记录id
     * @return
     */
    @Update("update common_collect_record set deleted = 1 where id = #{id}")
    public int deleteIdea(@Param("id") int id);

    /**
     * 查询学生历史记录里的所有课程id和表id
     * @param studentId 学生编号
     * @return HistoryId 学生所有历史记录类的表id和课程id
     */
    @Select("select DISTINCT courseId from common_student_browsing_history where  studenterId = #{studenterId} and DELETEd =0 order by id desc")
    public List<Integer> findHistoryCourse(@Param("studenterId") String studentId);


    /**
     * 用户删除历史记录里的课程
     * @param studenterId 学生编号
     * @param courseId 课程id
     * @return 返回操作结果 0代表操作失败，其他为成功
     */
    @Update("update common_student_browsing_history set deleted = 1 where courseId = #{courseId} and studenterId = #{studenterId}")
    public int deleteHistoryCourse(@Param("studenterId")String studenterId,@Param("courseId") Integer courseId);

    /**
     * 根据学生编号查询发送收到的信息实体类
     * @param studentId
     * @return MessageInfo 收到的信息详细内容
     */
    @Select("select id as messageId,message,date_format(createdt,'%Y-%m-%d %H:%i:%s') as sendTime,froms as sendId," +
            "studenterId as studentId,userType from common_tidings where studenterId = #{studentId}")
    public List<MessageInfo> queryMessageInfo(@Param("studentId")String studentId);

    /**
     * 发送者为学生 查询学生的相信信息
     * @param sendId 发送者编号
     * @return 发送者相信信息
     */
    @Select("select guid,userimage as imageUrl,nickname as name from sys_user where guid = " +
            "(select studentuserid from common_student_user where studenterId = #{sendId})")
    public MessageUserInfo queryUserInfo(@Param("sendId")String sendId);

    /**
     * 查询学生所有未完成的课程
     * @param studentId 学生编号
     * @return 所有未完成的课程id
     */
    @Select("select * from plan_course_info where planType = 1 and planFinish = 0 and studenterId = #{studentId} order by id desc")
    public List<CoursePojo> queryNotFinishCourse(@Param("studentId")String studentId);

    /**
     * 查询学生所有已完成的课程
     * @param studentId 学生编号
     * @return 所有已完成的课程id
     */
    @Select("select * from plan_course_info where planType = 1 and planFinish = 1 and studenterId = #{studentId} order by id desc")
    public List<CoursePojo> queryFinishCourse(@Param("studentId")String studentId);

    /**
     * 获取用户密码
     * @param guid 用户id
     * @return
     */
    @Select("select pwd from sys_user where guid = #{guid} limit 1")
    String getUserPass(@Param("guid") String guid);

    /**
     * 修改用户密码
     * @param guid 用户id
     * @param newPass 新密码
     * @return
     */
    @Update("update sys_user set pwd = #{newPass} where guid = #{guid}")
    Integer modifyPass(@Param("guid")String guid, @Param("newPass")String newPass);

    /**
     * 根虎用户手机号查询用户信息
     * @param mobilePhone
     * @return
     */
    @Select("select guid from sys_user where loginId = #{mobilePhone}")
    List<String> queryInfoByPhone(@Param("mobilePhone") String mobilePhone);

    /**
     * 更改用户手机号
     * @param request
     * @return
     */
    @Update("update sys_user set loginId = #{mobilePhone} where guid = #{guid}")
    int modifyPhone(RequestDTO request);


    /**
     * 提交用户建议
     * @param feedback
     * @return
     */
    @Insert("insert into  common_feedback (CONTENT, STUDENTERID) values (#{content},#{studenterId})")
    Integer submitOpinion(A_Feedback feedback);
    /**
     * 获取当前应用的最新版本信息
     * @param request
     * @return
     */
    @Select("select version,content as message,url as downUrl,urgentUpdate,size from sys_application_version where appType = #{appType} and deleted = 0 and isUse = 1 and type = #{type} order by createdt desc limit 1")
    ApplicationVersion checkUpdate(RequestDTO request);


    @Select("select * from my_course where studenterId = #{studenterId}")
    List<CoursePojo> myCourse(RequestDTO requestdto);

    @Select("select loginId from sys_user where guid = #{guid} and loginId = #{loginId}")
    List<String> checkPhone(RequestDTO request);

    /**
     * 查询用户的二维码
     * @param request
     * @return
     */
    @Select("select QRCode from sys_user where loginId = #{loginId} limit 1")
    String getQRCode(RequestDTO request);

    /**
     * 生成当前用户的二维码
     * @param loginId
     * @param url
     * @return
     */
    @Update("update sys_user set QRCode = #{url} where loginId = #{loginId}")
    int insertQRCode(@Param("loginId") String loginId,@Param("url") String url);

    /**
     * 获取学生端各平台app下载链接
     * @return
     */
    @Select("select appType, url as downUrl,size  from sys_application_version as a \n" +
            "where version = (select max(version) from sys_application_version where a.appType=appType) and appPlatform = 0")
    List<ApplicationVersion> getDownUrl();

    List<CoursePojo> getCourseInfo(@Param("list") List<Integer> courseId,@Param("studenterId") String studenterId);
}
