package com.lottery.ssq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.charset.Charset;

@EnableAsync
@EnableSwagger2
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource(locations = {"classpath:applicationContext.xml"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {

//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        // 设置超时 10s
//        requestFactory.setConnectTimeout(10000);
//        requestFactory.setReadTimeout(10000);

        RestTemplate restTemplate = new RestTemplate();

        // 使用utf-8字符集
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restTemplate;
    }

}