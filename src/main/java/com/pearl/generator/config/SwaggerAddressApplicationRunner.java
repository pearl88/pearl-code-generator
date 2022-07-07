package com.pearl.generator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author TangDan
 * @version 1.0
 * @since 2022/7/7
 */
@Slf4j
@Component
public class SwaggerAddressApplicationRunner implements ApplicationRunner {

    @Resource
    Environment environment;

    @Override
    public void run(ApplicationArguments args) throws UnknownHostException {
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "Doc: \thttp://{}:{}/doc.html\n\t" +
                        "----------------------------------------------------------",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                host, port,
                host, port);
    }
}

