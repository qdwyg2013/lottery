package com.lottery.ssq.interceptor;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;

/**
 * Create by tianjiaqin 2019/11/20-12-43
 */
@Configuration
public class JacksonSeriConfiguration extends ObjectMapper {


    /**
     * 将所有的Long，Integer转化为 String类型 。适配原有的 ios强制类型
     */
    public JacksonSeriConfiguration() {

        // 序列换成json时,将所有的long变成string,因为js中得数字类型不能包含所有的java long值
        SimpleModule simpleModule = new SimpleModule("JacksonModule", new Version(1, 0, 0, "", "com.dangdang", "jackson"));
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(Integer.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Integer.TYPE, ToStringSerializer.instance);
        registerModule(simpleModule);
    }


    //@Bean
    //public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
    //    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    //    ObjectMapper mapper = new ObjectMapper();
    //
    //    //日期格式转换
    //    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    //
    //    //Long类型转String类型
    //    SimpleModule simpleModule = new SimpleModule();
    //    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    //    simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
    //    simpleModule.addSerializer(Integer.class, ToStringSerializer.instance);
    //    simpleModule.addSerializer(Integer.TYPE, ToStringSerializer.instance);
    //
    //    mapper.registerModule(simpleModule);
    //
    //    converter.setObjectMapper(mapper);
    //    return converter;
    //}
}
