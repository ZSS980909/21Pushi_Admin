package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.HomePageSearch;
import com.ershiyi.domain.entity.A_KnowContent;
import com.ershiyi.domain.entity.Search;
import com.ershiyi.service.HomePageSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *主页模糊搜索
 */
@RestController
@RequestMapping("/hunt")
@Api(value = "知识主页", tags = {"首页知识主页"})
public class HomePageSearchController {
    @Autowired
    private HomePageSearchService homepagesearchService;
//    /**
//     *首页模糊搜索   作废  已修改需求  查找知识点
//     */
//    @PostMapping("/search")
//    @ResponseBody
//    @ApiOperation(value = "模糊搜索", notes = "首页模糊搜索")
//    @Deprecated
//    public AbstractBaseResult Search(@RequestBody HomePageSearch search) {
//        //return RespEnum.OK.result(homepagesearchService.Search(search));
//        return null;
//    }

    /**
     *首页模糊搜索   搜索知识点
     */
    @PostMapping("/searchKnowledge")
    @ResponseBody
    @ApiOperation(value = "首页知识点模糊搜索", notes = "首页知识点模糊搜索")
    @Deprecated
    public AbstractBaseResult searchKnowledge(@RequestBody A_KnowContent search) {
        return RespEnum.OK.result(homepagesearchService.searchKnowledge(search));
    }



    /**
     * 首页等待课程中查询 作废 已经移植到CourseController
     */
    @PostMapping("/waitCourse")
    @ResponseBody
    @ApiOperation(value = "待学课程查询", notes = "待学课程查询")
    @Deprecated
    public AbstractBaseResult waitCourse(@RequestBody HomePageSearch search) {
        return RespEnum.OK.result(homepagesearchService.waitCourse(search));
    }

    /**
     * 根据知识点id查看课程信息
     */
    @PostMapping("/knowledgeByCourse")
    @ResponseBody
    @ApiOperation(value = "根据知识点查看课程信息", notes = "根据知识点查看课程信息")
    public AbstractBaseResult knowledgeByCourse(@RequestBody HomePageSearch search) {
        return RespEnum.OK.result(homepagesearchService.knowledgeByCourse(search));
    }
    /**
     * 主页pad搜索接口(最终版)
     */
    @PostMapping("/search")
    @ResponseBody
    @ApiOperation(value = "搜索课程,知识点,题目", notes = "搜索课程,知识点,题目")
    public AbstractBaseResult search(@RequestBody Search search) {
        return RespEnum.OK.result(homepagesearchService.search(search));
    }
    /**
     * 课程pad搜索接口详情
     */
    @PostMapping("/searchContext")
    @ResponseBody
    @ApiOperation(value = "搜索课程详情", notes = "搜索课程详情")
    public AbstractBaseResult searchContext(@RequestBody Search search) {
        return RespEnum.OK.result(homepagesearchService.searchContext(search));
    }
    /**
     * 知识点pad搜索接口详情
     */
    @PostMapping("/searchKnowledgeDetails")
    @ResponseBody
    @ApiOperation(value = "搜索知识点详情", notes = "搜索知识点详情")
    public AbstractBaseResult searchKnowledgedetails(@RequestBody Search search) {
        return RespEnum.OK.result(homepagesearchService.searchKnowledgedetails(search));
    }
    /**
     * 题目pad搜索接口详情
     */
    @PostMapping("/searchQuestionDetails")
    @ResponseBody
    @ApiOperation(value = "搜索题目详情", notes = "搜索题目详情")
    public AbstractBaseResult searchQuestiondetails(@RequestBody Search search) {
        return RespEnum.OK.result(homepagesearchService.searchQuestiondetails(search));
    }
}
