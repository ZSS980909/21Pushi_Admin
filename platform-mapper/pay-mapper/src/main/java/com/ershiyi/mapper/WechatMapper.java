package com.ershiyi.mapper;

import com.ershiyi.dto.AlipayInDTO;
import com.ershiyi.dto.WechatPayInDTO;
import tk.mybatis.mapper.AbstractMapper;

import java.util.Map;

public interface WechatMapper extends AbstractMapper<WechatPayInDTO> {
    //插入预订单信息
    Integer insertorder(WechatPayInDTO inDTO);

    String selectIntegral(WechatPayInDTO inDTO);

    Integer updateOrder(Map<String, String> mapUpdate);
}
