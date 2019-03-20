package com.wenhao.shop.sevice.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "微信注册码验证接口")
public interface VerificaCodeService {

    //@Headers({"Content-Type: application/json", "Accept: application/json"})
//    @RequestLine(value = "POST /verificaWeixinCode")
    @PostMapping("/verificaWeixinCode")
    @ApiOperation(value = "根据手机号验证token是否正确")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mobile", dataType = "String", required = true, value = "用户手机号码"),
            @ApiImplicitParam(paramType = "query", name = "registCode", dataType = "String", required = true, value = "微信验证码")})
    BaseResponse<JSONObject> verificaWeixinCode(@RequestParam(value = "mobile") String phone, @RequestParam(value = "registCode") String weixinCode);
}
