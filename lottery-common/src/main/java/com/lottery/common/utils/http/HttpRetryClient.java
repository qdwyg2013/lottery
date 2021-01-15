package com.lottery.common.utils.http;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;

@Component
public class HttpRetryClient {

    private static final HostnameVerifier PROMISCUOUS_VERIFIER = (s, sslSession) -> true;

    @Bean
    public RestTemplate commonRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //验证主机名和服务器验证方案的匹配是可接受的

        restTemplate.setRequestFactory(getRequestFactory(10000, 10000));

        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        restTemplate.getMessageConverters().set(1, stringConverter);

        return restTemplate;
    }

    private SimpleClientHttpRequestFactory getRequestFactory(int readTimeout, int connectTimeout) {

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                if (connection instanceof HttpsURLConnection) {
                    ((HttpsURLConnection) connection).setHostnameVerifier(PROMISCUOUS_VERIFIER);
                }
                super.prepareConnection(connection, httpMethod);
            }
        };
        requestFactory.setReadTimeout(readTimeout);
        requestFactory.setConnectTimeout(connectTimeout);

        return requestFactory;
    }
}
