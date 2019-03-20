package com.wenhao.shop.service.weixin.mp.handler;

import com.wenhao.member.output.dto.UserOutDTO;
import com.wenhao.base.BaseResponse;
import com.wenhao.constants.Constants;
import com.wenhao.core.utils.RegexUtils;
import com.wenhao.shop.service.weixin.feign.MemberServiceFeign;
import com.wenhao.shop.service.weixin.mp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Value("${mayikt.weixin.registration.code.message}")
    private String registrationCodeMessage;

    @Value("${mayikt.weixin.default.registration.code.message}")
    private String defaultRegistrationCodeMessage;

    /*@Autowired
    private RedisUtil redisUtil;*/

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    //设置stirng 有过期时间
    public void setString(String key, String data, Long exTime) {
        stringRedisTemplate.opsForValue().set(key, data);
        if (exTime != null) {
            stringRedisTemplate.expire(key, exTime, TimeUnit.SECONDS);
        }
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                    && weixinService.getKefuService().kfOnlineList()
                    .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                        .fromUser(wxMessage.getToUser())
                        .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        //TODO 组装回复消息
        //String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);
        //String content = "收到信息内容：" + "谢谢";
        String fromContent = wxMessage.getContent();
        //校验手机号
        if (RegexUtils.checkMobile(fromContent)) {
            //根据手机号码调用会员服务接口查询用户信息是否存在
            BaseResponse<UserOutDTO> resultExistUserInfo = memberServiceFeign.existMobile(fromContent);
            if (resultExistUserInfo.getCode().equals(Constants.HTTP_RES_CODE_200)) {
                return new TextBuilder().build("该手机号码" + fromContent + "已经存在", wxMessage, weixinService);
            }
            if (!resultExistUserInfo.getCode().equals(Constants.HTTP_RES_CODE_EXISTMOBILE_202)) {
                return new TextBuilder().build(resultExistUserInfo.getMsg(), wxMessage, weixinService);
            }
            //生成code
            int registCode = registCode();
            //替换成验证码
            String content = registrationCodeMessage.format(registrationCodeMessage, registCode);
            //redisUtil.delete("");
            setString(Constants.WEIXINCODE_KEY + fromContent, registCode + "", Constants.WEIXINCODE_TIMEOUT);
            //返回code
            return new TextBuilder().build(content, wxMessage, weixinService);
        }
        //返回默认消息
        return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);

    }

    //获取注册码
    private int registCode() {
        int registCode = (int) (Math.random() * 9000 + 1000);
        return registCode;
    }

}
