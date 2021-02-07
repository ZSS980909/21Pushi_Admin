package com.ershiyi.service;

import com.ershiyi.dto.RequestDTO;
import com.ershiyi.entity.CommentInfo;
import com.ershiyi.entity.TeacherInfo;

import java.util.List;

/**
 * @Description: 家长端补教系统服务类接口
 * @author: zss98
 * @date: 2020-12-23 09:30
 * @version: 1.0
 */
public interface TutoringService {


    /**
     * 查询老师列表
     * @param request
     * @return
     */
    List<TeacherInfo> TeacherList(RequestDTO request);

    /**
     * 根据名称搜索老师信息
     * @param request
     * @return
     */
    List<TeacherInfo> SearchTeacher(RequestDTO request);

    List<CommentInfo> CommentInfo(RequestDTO request);
}
