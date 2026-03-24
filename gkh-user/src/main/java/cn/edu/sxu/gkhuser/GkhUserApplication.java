package cn.edu.sxu.gkhuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GkhUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GkhUserApplication.class, args);
    }
}
