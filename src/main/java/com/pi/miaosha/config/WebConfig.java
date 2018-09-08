package com.pi.miaosha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 12:11
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private UserArgumentResolvers userArgumentResolvers;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolvers);
    }
}
