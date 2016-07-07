package com.teamtreehouse.service;

import com.teamtreehouse.config.AppConfig;
import com.teamtreehouse.service.resttemplate.weather.WeatherServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class WeatherServiceTest {

    @Configuration
    @PropertySource("api.properties")
    public static class TestConfig {
        @Autowired
        private Environment env;

        @Bean
        public RestTemplate restTemplate() {
            return AppConfig.defaultRestTemplate();
        }

        @Bean
        public WeatherService weatherService() {
            WeatherService service = new WeatherServiceImpl(
                env.getProperty("weather.api.name"),
                env.getProperty("weather.api.key"),
                env.getProperty("weather.api.host")
            );
            return service;
        }
    }
}
