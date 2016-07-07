package com.teamtreehouse.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.teamtreehouse.service.TimestampDeserializer;
import com.teamtreehouse.web.ReferrerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@PropertySource("api.properties")
public class AppConfig extends WebMvcConfigurerAdapter {
    @Value("${api.timeout}")
    private int timeout;

    //To resolve ${} in @Value annotations
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rt = defaultRestTemplate();
        rt.setRequestFactory(clientHttpRequestFactory());
        return rt;
    }

    public static RestTemplate defaultRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper jacksonObjectMapper = new ObjectMapper();

        SimpleModule timestampModule = new SimpleModule("TimestampModule", new Version(1,0,0,null,null,null)).addDeserializer(Instant.class, new TimestampDeserializer());
        jacksonObjectMapper.registerModule(timestampModule);
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(jacksonObjectMapper);
        restTemplate.setMessageConverters(Arrays.asList(jsonMessageConverter));
        return restTemplate;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(timeout);
        factory.setConnectTimeout(timeout);
        return factory;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReferrerInterceptor());
    }
}
