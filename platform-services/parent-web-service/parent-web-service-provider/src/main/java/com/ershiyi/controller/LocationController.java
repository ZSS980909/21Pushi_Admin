package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.LocationRequestDTO;
import com.ershiyi.service.LocationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 百度地图
 * 获取经纬度类
 */
@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService service;
    /**
     *百度地图
     * 接收定位
     */
    @PostMapping("/getLocation")
    @ResponseBody
    @ApiOperation(value = "百度地图", notes = "接收定位")
    public AbstractBaseResult getLocation(@RequestBody LocationRequestDTO localtionrequest) {
        return RespEnum.OK.result(service.getLocation(localtionrequest));
    }

    /**
     *百度地图
     * 查询该学生定位
     */
    @PostMapping("/queryLocation")
    @ResponseBody
    @ApiOperation(value = "百度地图", notes = "查询该定位")
    public AbstractBaseResult queryLocation(@RequestBody LocationRequestDTO localtionrequest) {
        return RespEnum.OK.result(service.queryLocation(localtionrequest));
    }
}
