package com.wenhao.member;

import com.wenhao.base.BaseResponse;
import com.wenhao.member.input.dto.UserLoginInpDto;
import com.wenhao.member.output.dto.UserOutDTO;
import com.wenhao.weixin.input.dto.AppInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "会员服务")
public interface MemberService {

    @ApiOperation(value = "会员服务调用微信服务")
    @GetMapping("/memberToWeixin")
    BaseResponse<AppInpDTO> memberToWeixin();

    /**
     * 根据手机号码查询是否已经存在,如果存在返回当前用户信息
     *
     * @param mobile
     * @return
     */
    @ApiOperation(value = "根据手机号码查询是否已经存在")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mobile", dataType = "String", required = true, value = "用户手机号码"),})
    @PostMapping("/existMobile")
    BaseResponse<UserOutDTO> existMobile(@RequestParam("mobile") String mobile);

    /**
     * 根据token查询用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "/getUserInfo")
    BaseResponse<UserOutDTO> getInfo(@RequestParam("token") String token);


    /**
     * SSO认证系统登陆接口
     *
     * @param userLoginInpDTO
     * @return
     */
    @PostMapping("/ssoLogin")
    public BaseResponse<UserOutDTO> ssoLogin(@RequestBody UserLoginInpDto userLoginInpDTO);


}
