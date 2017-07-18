package cn.ellacat.tools.alarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author wjc133
 */
@SpringBootApplication
public class Bootstrap extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Bootstrap.class);
    }

    public static void main(String[] args) {
        System.out.println("Application Booting...");
        SpringApplication.run(Bootstrap.class, args);
    }
}
