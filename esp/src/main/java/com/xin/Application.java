package com.xin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */

@SpringBootApplication
@MapperScan(basePackages = {"com.xin.db.dao", "com.xin.self.db.dao"})

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
