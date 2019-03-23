package com.wenhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 排除数据库连接
 */
@SpringBootApplication(scanBasePackages = {"com.wenhao"},
        exclude = {DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class})
@EnableFeignClients
public class PortalWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalWebApplication.class, args);
    }
}