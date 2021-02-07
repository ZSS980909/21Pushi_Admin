package com.ershiyi.controller;


import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.JpushPojo;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.feign.JpushFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Management")
@Api(value = "家长管控", tags = {"家长管控"})
public class ManagementController {
    @Autowired
    private JpushFeign jupshfeign;
    public static Log log = LogFactory.getLog(ManagementController.class);
    /**
     * 家长锁屏
     */
    @PostMapping("/screen")
    @ResponseBody
    @ApiOperation(value = "家长锁屏", notes = "家长锁屏",httpMethod = "POST")
    public void screen(@RequestBody JpushPojo jpush) {
        //调用极光项目传入
        log.info("锁屏设备标识为"+jpush);
        log.info(jpush.getSendType()+"AAA");
      //  log.info(jpush.getRegisktrationId()+"bbB");
        log.info(jpush.getMsg()+"CCC");
        jupshfeign.JpushBytype();
        //jupshfeign.JpushBytype(jpush.getSendtype(),jpush.getRegistrationId(),jpush.getMsg());
        //return  null;
        //return RespEnum.OK.result();
    }
}
