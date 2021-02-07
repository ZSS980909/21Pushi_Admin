package com.ershiyi.genid;

import cn.hutool.core.util.IdUtil;
import tk.mybatis.mapper.genid.GenId;

public class GenerateUUID implements GenId<String> {

    @Override
    public String genId(String s, String s1) {
        return IdUtil.objectId();
    }

}
