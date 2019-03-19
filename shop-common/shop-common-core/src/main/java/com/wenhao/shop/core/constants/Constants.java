package com.wenhao.shop.core.constants;

/**
 * 常量
 */
public interface Constants {
    //响应请求成功
    String HTTP_RES_CODE_200_VALUE = "success";

    //响应请求失败
    String HTTP_RES_CODE_500_VALUE = "fail";

    //响应请求成功code
    Integer HTTP_RES_CODE_200 = 200;

    //系统错误
    Integer HTTP_RES_CODE_500 = 500;

    //未关联QQ帐号
    Integer HTTP_RES_CODE_201 = 201;

    //发送邮件
    String MSG_EMAIL = "email";

    //会员token
    String TOKEN_MEMBER = "TOKEN_MEMBER";

    //用户有效期
    Long TOKEN_MEMBER_TIME = (long) (60 * 60 * 24 * 90);
    int COOKIE_TOKEN_MEMBER_TIME = (60 * 60 * 24 * 90);

    //cookie 会员 token
    String COOKIE_MEMBER_TOKEN = "cookie_member_token";

    //微信code
    String WEIXINCODE_KEY = "weixin.code";

    //微信注册码有效时间
    Long WEIXINCODE_TIMEOUT = 1800L;

    //手机号码已经存在
    int HTTP_RES_CODE_EXISTMOBILE_202 = 202;
}
