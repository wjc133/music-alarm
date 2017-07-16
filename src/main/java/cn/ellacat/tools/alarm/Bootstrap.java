package cn.ellacat.tools.alarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wjc133
 */
@SpringBootApplication
public class Bootstrap {
    public static void main(String[] args) {
        System.out.println("Application Booting...");
        SpringApplication.run(Bootstrap.class, args);
    }
}
