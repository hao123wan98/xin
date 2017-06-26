package com.xin;

import com.xin.aop.ControllerInterceptor;
import com.xin.user.service.TokenService;
import com.zhenhr.common.TokenException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan(basePackages = {"com.xin.db.dao", "com.xin.self.db.dao"})

public class SpringBootApiApplication extends WebMvcConfigurerAdapter {
    @Autowired
    ControllerInterceptor controllerInterceptor;


    public static void main(String[] args) {
        SpringApplication.run(SpringBootApiApplication.class, args);
    }


    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controllerInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
