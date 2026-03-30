package cn.edu.sxu.gkhcourse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.sxu.gkhcourse.mapper")
public class SkhCourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkhCourseApplication.class, args);
    }
}
