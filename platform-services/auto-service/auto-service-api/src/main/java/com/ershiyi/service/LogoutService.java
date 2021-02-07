package com.ershiyi.service;

import com.ershiyi.vo.TokenVO;

public interface LogoutService {

    TokenVO refresh();

    TokenVO exit();
}
