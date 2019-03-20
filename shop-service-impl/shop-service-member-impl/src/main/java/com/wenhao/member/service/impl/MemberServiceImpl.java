package com.wenhao.member.service.impl;

import com.wenhao.base.BaseApiService;
import com.wenhao.base.BaseResponse;
import com.wenhao.constants.Constants;
import com.wenhao.core.bean.MeiteBeanUtils;
import com.wenhao.member.fegin.WeixinFegin;
import com.wenhao.member.mapper.UserMapper;
import com.wenhao.member.output.dto.UserOutDTO;
import com.wenhao.member.MemberService;
import com.wenhao.weixin.input.dto.AppInpDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl extends BaseApiService<UserOutDTO> implements MemberService {

    @Autowired
    private WeixinFegin weixinFegin;

    @Autowired
    private UserMapper userMapper;


    @Override
    public BaseResponse<AppInpDTO> memberToWeixin() {
        return weixinFegin.getApp();
    }

    @Override
    public BaseResponse<UserOutDTO> existMobile(String mobile) {
        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        UserOutDTO userEntity = userMapper.existMobile(mobile);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
        }
        return setResultSuccess(MeiteBeanUtils.dtoToDo(userEntity, UserOutDTO.class));
    }

}
