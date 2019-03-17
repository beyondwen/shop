package com.wenhao.shop.sevice.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.shop.core.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "微信注册码验证接口")
public interface VerificaCodeService {

    @ApiOperation(value = "根据手机号验证token是否正确")
    @GetMapping("/verificaWeixinCode")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "用户手机号码"),
            @ApiImplicitParam(paramType = "query", name = "weixinCode", dataType = "String", required = true, value = "微信验证码")})
    BaseResponse<JSONObject> verificaWeixinCode(String phone, String weixinCode);
}
