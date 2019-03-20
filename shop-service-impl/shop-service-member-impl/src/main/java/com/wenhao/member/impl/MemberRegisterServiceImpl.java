package com.wenhao.member.impl;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.core.error.GlobalExceptionHandler;
import com.wenhao.member.input.dto.UserInpDTO;
import com.wenhao.base.BaseApiService;
import com.wenhao.base.BaseResponse;
import com.wenhao.constants.Constants;
import com.wenhao.core.utils.MD5Util;
import com.wenhao.member.enity.UserDo;
import com.wenhao.member.fegin.VerificaCodeServiceFegin;
import com.wenhao.member.mapper.UserMapper;
import com.wenhao.shop.sevice.member.service.MemberRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificaCodeServiceFegin verificaCodeServiceFegin;

    @Transactional
    public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO, String registCode) {
        //参数验证
        String userName = userInpDTO.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return setResultError("用户名称不能为空");
        }
        String mobile = userInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号不能为空");
        }
        String password = userInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空");
        }
        //验证注册码是否正确
        /*BaseResponse<JSONObject> verificaWeixinCode = verificaCodeServiceFegin.verificaWeixinCode(mobile, registCode);
        if (!verificaWeixinCode.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(verificaWeixinCode.getMsg());
        }*/
        //对用户密码加密
        String newPassword = MD5Util.MD5(password);
        userInpDTO.setPassword(newPassword);
        //调用数据库插入数据 将请求的dto转成do
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userInpDTO, userDo);
        //更新数据库
        return userMapper.register(userDo) > 0 ? setResultSuccess("注册成功") : setResultError("注册失败");
    }
}
