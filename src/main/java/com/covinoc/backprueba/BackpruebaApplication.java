package com.covinoc.backprueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class BackpruebaApplication extends SpringBootServletInitializer {

	/*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }*/
	public static void main(String[] args) {
		SpringApplication.run(BackpruebaApplication.class, args);
	}

	//private static Class<BackpruebaApplication> applicationClass = BackpruebaApplication.class;

}
