package com.sam.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * @author sumit
 *
 */
@SpringBootApplication
public class AppAuth extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppAuth.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppAuth.class);
	}

}
