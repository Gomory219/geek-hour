package cn.edu.sxu.gkhgateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GkhGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GkhGatewayApplication.class, args);
    }
}
