package com.wenhao.session.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//这个类用配置redis服务器的连接
//maxInactiveIntervalInSeconds为SpringSession的过期时间（单位：秒）
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SessionConfig {

    // 冒号后的值为没有配置文件时，制动装载的默认值
    @Value("${redis.host:192.168.2.146}")
    String hostName;
    @Value("${redis.port:6379}")
    int port;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        RedisStandaloneConfiguration standaloneConfiguration = connection.getStandaloneConfiguration();
        standaloneConfiguration.setHostName(hostName);
        standaloneConfiguration.setPort(port);
        return connection;
    }
}
