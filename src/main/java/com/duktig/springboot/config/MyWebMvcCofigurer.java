package com.duktig.springboot.config;

import com.duktig.springboot.common.Constants;
import com.duktig.springboot.config.handle.TokenToUserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class MyWebMvcCofigurer implements WebMvcConfigurer {
    @Resource
    private TokenToUserResolver token;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_PATH);
    }
    /*
     * @Description: 注解处理方法
     * @Creator: flanderschuang
     * @Date: 2021/3/16 12:38 下午
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(token);
    }
}
