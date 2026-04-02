package cn.edu.sxu.gkhuser;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDubbo
@MapperScan("cn.edu.sxu.gkhuser.mapper")
public class GkhUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GkhUserApplication.class, args);
    }
}
