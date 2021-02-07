package com.ershiyi.service.Impl;

import com.ershiyi.dto.RequestDTO;
import com.ershiyi.entity.CommentInfo;
import com.ershiyi.entity.TeacherInfo;
import com.ershiyi.entity.UserInfo;
import com.ershiyi.mapper.TutoringMapper;
import com.ershiyi.service.TutoringService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 家长端补教系统服务层实现类
 * @author: zss98
 * @date: 2020-12-23 09:31
 * @version: 1.0
 */
@Service
public class TutoringServiceImpl implements TutoringService {

    @Autowired
    public TutoringMapper mapper;

    /**
     * 获取老师列表
     *
     * @param request
     * @return
     */
    @Override
    public List<TeacherInfo> TeacherList(RequestDTO request) {
        // 开启分页
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        // 获取老师信息
        List<TeacherInfo> results = mapper.TeacherInfo(request);
        // 获取老师标签和可选科目信息
        for (TeacherInfo result : results) {
            result.setSubjects(mapper.queryPossibleSubjects(result));
            result.setLabel(mapper.queryLabel(result));
        }
        return results;
    }

    /**
     * 根据名称搜索老师
     * @param request
     * @return
     */
    @Override
    public List<TeacherInfo> SearchTeacher(RequestDTO request) {
        // 开启分页
        PageHelper.startPage(request.getPageNumber(), request.getPageSize());
        List<TeacherInfo> results = mapper.SearchTeacher("%" + request.getName() + "%");
        for (TeacherInfo result : results) {
            result.setSubjects(mapper.queryPossibleSubjects(result));
            result.setLabel(mapper.queryLabel(result));
        }
        return results;
    }

    /**
     * 查看当前老师的评论
     * @param request
     * @return
     */
    @Override
    public List<CommentInfo> CommentInfo(RequestDTO request) {
        // 记录当前老师的浏览记录
        mapper.insertView(request);
        // 开启分页
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        // 获取评论列表
        List<CommentInfo> results = mapper.getCommentInfo(request);
        for (CommentInfo result : results) {
            UserInfo user = new UserInfo();
            if(result.getUserType()==1){
                // 评价为学生
                user = mapper.getStudentUser(result);
                result.setLearnTime(mapper.getStudentLearnTime(result));
            }else{
                // 评价为老师
                user = mapper.getParentUser(result);
                result.setLearnTime(mapper.getParentLearnTime(result));
            }
            result.setNickName(user.getNickName());
            result.setUserImage(user.getUserImage());
        }
        return results;
    }


}
