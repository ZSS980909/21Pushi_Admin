package com.ershiyi.domain;

import java.io.Serializable;

public class UserLogins  extends AbstractBaseDomain implements Serializable {
    /**
     *学生id
     */
    private String studenterId;
    /**
     * 登录名（默认手机号）
     */
    private String loginid;

}
