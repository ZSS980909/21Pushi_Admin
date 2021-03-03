package com.ershiyi.feign.fallback;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dto.JpushPojo;
import com.ershiyi.feign.JpushFeign;
import org.springframework.stereotype.Component;

@Component
public class JpushFeignFallBack implements JpushFeign {
        //String Sendtype, String RegistrationId, String Msg
    @Override
    public AbstractBaseResult JpushBytype() {
            return null;
    }
}
