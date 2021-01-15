package com.lottery.common.utils.http;

import org.springframework.web.client.RestTemplate;


public interface Task<T> {

     T excutor(RestTemplate restTemplate);
}
