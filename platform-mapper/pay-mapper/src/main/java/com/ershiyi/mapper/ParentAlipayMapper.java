package com.ershiyi.mapper;

import com.ershiyi.dto.AlipayInDTO;
import com.ershiyi.dto.ParentAlipayInDTO;
import tk.mybatis.mapper.AbstractMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ParentAlipayMapper extends AbstractMapper<AlipayInDTO> {
    Integer parentInsertOrder(ParentAlipayInDTO parentalipayindto);

    void parentalipayOrderNotify(HttpServletRequest request, HttpServletResponse response);

    Integer updateOrder(Map<String, String> params);
}
