package com.sam.sso;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.sam.sso.filter.JwtFilter;

/**
 * @author sumit
 *
 */
@SpringBootApplication
public class AppResource extends SpringBootServletInitializer {

	@Value("${services.auth}")
	private String authService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppResource.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppResource.class);
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		/*Service URL*/
		registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
		/*After login landing URL*/
		registrationBean.addUrlPatterns("/protected-resource");
		return registrationBean;
	}

}
