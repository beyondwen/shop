package com.wenhao.member.mapper;

import com.wenhao.member.enity.UserTokenDo;
import feign.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenMapper {

    /**
     * 根据用户id和登录类型查询用户token
     *
     * @param userId    用户id
     * @param loginType 登录类型
     * @return
     */
    @Select("SELECT ID id,TOKEN token, LOGIN_TYPE loginType,DEVICE_INFO deviceInfo,IS_AVAILABLE isAvaliable," +
            "USER_ID userId,CREATE_TIME createTime, UPDATE_TIME updateTime FROM meite_user_token WHERE " +
            "USER_ID = #{0} AND LOGIN_TYPE =#{1} AND IS_AVAILABLE = '0';")
    UserTokenDo selectByUserIdAndLoginType(@Param("userId") Long userId, @Param("loginType") String loginType);

    /**
     * 根据用户id和登录类型更新token状态
     *
     * @param userId
     * @param loginType
     * @return
     *//*
    @Update("UPDATE meite_user_token SET IS_AVAILABLE = '1',UPDATE_TIME=NOW() " +
            "WHERE USER_ID = #{userId} AND LOGIN_TYPE =#{loginType} ")
    int updateTokenAvaliable(@Param("userId") Long userId, @Param("loginType") String loginType);*/

    /**
     * 根据用户token更新token状态
     *
     * @param token token
     * @return
     */
    @Update("UPDATE meite_user_token SET IS_AVAILABLE = '1',UPDATE_TIME=NOW() " +
            "WHERE TOKEN = #{token};")
    int updateTokenAvaliable(@Param("token") String token);

    /**
     * 插入用户token到数据库
     *
     * @param userTokenDo
     * @return
     */
    @Insert("INSERT INTO `meite_user_token`(TOKEN,LOGIN_TYPE,DEVICE_INFO,IS_AVAILABLE,USER_ID,CREATE_TIME,UPDATE_TIME) " +
            "VALUES (#{token}, #{loginType}, #{deviceInfo}, 0, #{userId}, #{createTime}, #{updateTime})")
    int insertUserToken(UserTokenDo userTokenDo);
}
