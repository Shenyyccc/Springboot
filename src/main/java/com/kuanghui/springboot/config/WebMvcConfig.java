package com.kuanghui.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//扩展SpringMVC：1.使用@Configuration 2.实现WebMvcConfigurer接口 3.不能使用@EnableWebMvc
@Configuration
//这个注解表示Mvc被全面接管
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }

    //原来配拦截器是实现拦截器的接口，现在是实现配置类接口，重写里面的方法来实现
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginHandlerIntercepter())
            .addPathPatterns("/**")
            .excludePathPatterns("/","/index.html","/user/login","/css/*","/img/*","/js/*");
    }

    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //查看DispatcherServlet类中的doDispathcher方法，发现视图解析器会生效，说明我们想自定义视图解析器
    //只需自己写一个视图解析器并且注入到Bean里面即可，SpringBoot会自动帮我们装配上
    //DispatcherServlet中有多个视图解析器，它会遍历每一个视图解析器获取候选视图并选出最好的视图
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    //实现了视图解析器ViewResolver接口的类，我们就可以把它看作视图解析器
    public static class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }

}
