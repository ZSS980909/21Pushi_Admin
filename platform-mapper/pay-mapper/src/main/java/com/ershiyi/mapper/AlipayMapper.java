package com.ershiyi.mapper;

import com.ershiyi.dto.AlipayInDTO;
import tk.mybatis.mapper.AbstractMapper;

import java.util.Map;

public interface AlipayMapper extends AbstractMapper<AlipayInDTO> {
    //插入预订单信息
    Integer insertorder(AlipayInDTO inDTO);


    String selectIntegral(AlipayInDTO inDTO);

    Integer updateOrder(Map<String, String> mapUpdate);

    Integer updateOrderbyfather(Map<String, String> mapUpdate);
}
