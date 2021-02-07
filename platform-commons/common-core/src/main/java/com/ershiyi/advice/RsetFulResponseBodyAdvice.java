package com.ershiyi.advice;

import com.ershiyi.dist.RespEnum;
import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.common.dto.BaseResultFactory;
import com.ershiyi.exception.PlatformServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
public class RsetFulResponseBodyAdvice implements ResponseBodyAdvice<AbstractBaseResult> {

    protected BaseResultFactory baseResultFactory = BaseResultFactory.getInstance();

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if(methodParameter.getMethod().getReturnType().isAssignableFrom(AbstractBaseResult.class)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AbstractBaseResult beforeBodyWrite(AbstractBaseResult abstractBaseResult, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ServletServerHttpResponse servletServerHttpResponse = (ServletServerHttpResponse)serverHttpResponse;
        servletServerHttpResponse.getServletResponse().setStatus(abstractBaseResult.getCode());
        return abstractBaseResult;
    }

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public AbstractBaseResult exceptionHandler(Throwable e){
        e.printStackTrace();
        //log.error("运行异常", e);
        return baseResultFactory.build(RespEnum.ERROR, e);
    }

    @ResponseBody
    @ExceptionHandler(value = PlatformServiceException.class)
    public AbstractBaseResult platformServiceExceptionHandler(PlatformServiceException e){
        e.printStackTrace();
      //  log.error("平台异常", e);
        return baseResultFactory.build(e.getRespEnum(), e);
    }

}
