package com.ershiyi.advice;

import com.ershiyi.dist.RespEnum;
import com.ershiyi.common.dto.AbstractBaseResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@ApiIgnore
@Controller
public class NotFoundException implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public AbstractBaseResult error(HttpServletRequest request) {
        throw  RespEnum.NOT_FOUND.throwException("无效请求");
    }
}
