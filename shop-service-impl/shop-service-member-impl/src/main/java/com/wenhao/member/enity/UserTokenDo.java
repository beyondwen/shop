package com.wenhao.member.enity;

import com.wenhao.base.BaseDo;
import lombok.Data;

@Data
public class UserTokenDo extends BaseDo {

    /**
     * 登录类型
     */
    private String loginType;

    /**
     * 设备信息
     */
    private String deviceInfo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * token
     */
    private String token;
}
