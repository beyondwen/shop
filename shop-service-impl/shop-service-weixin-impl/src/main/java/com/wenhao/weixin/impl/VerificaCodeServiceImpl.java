package com.wenhao.weixin.impl;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.base.BaseApiService;
import com.wenhao.base.BaseResponse;
import com.wenhao.constants.Constants;
import com.wenhao.core.utils.RedisUtil;
import com.wenhao.shop.sevice.weixin.service.VerificaCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificaCodeServiceImpl extends BaseApiService<JSONObject> implements VerificaCodeService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public BaseResponse<JSONObject> verificaWeixinCode(String phone, String weixinCode) {
        if (StringUtils.isEmpty(phone)) {
            return setResultError("手机号码不能为空");
        }
        if (StringUtils.isEmpty(weixinCode)) {
            return setResultError("微信验证码不能为空");
        }
        String weixinCodeKey = Constants.WEIXINCODE_KEY + phone;
        String redisCode = redisUtil.getString(weixinCodeKey);
        if (StringUtils.isEmpty(redisCode)) {
            return setResultError("注册码可能已经过期");
        }
        if (!redisCode.equals(weixinCode)) {
            return setResultError("注册码不正确");
        }
        redisUtil.delete(weixinCodeKey);
        return setResultSuccess("验证码比对正确");
    }
}
