package cn.edu.sxu.gkhinteraction;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.sxu.gkhinteraction.mapper")
@EnableDubbo
public class GkhInteractionApplication {
    public static void main(String[] args) {
        SpringApplication.run(GkhInteractionApplication.class, args);
    }
}
