package com.wenhao.shop.zuul;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {

    @ApolloConfig
    private Config config; //inject config for namespace application

    @Override
    public void run(String... args) throws Exception {
//        log.info("###########MyCommandLineRunner启动成功################");
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {
//                log.debug("###分布式配置文件监听###" + configChangeEvent.changedKeys().toString());
            }
        });
    }
}
