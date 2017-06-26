package com.xin;

import com.xin.aop.ControllerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan(basePackages = {"com.xin.db.dao", "com.xin.self.db.dao"})

public class SpringBootApiApplication extends WebMvcConfigurerAdapter {

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
        registry.addInterceptor(new ControllerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
