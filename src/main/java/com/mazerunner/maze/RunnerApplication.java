package com.mazerunner.maze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@SpringBootApplication
public class RunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunnerApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean simpleCorsFilter(){
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(false);
//
//		config.setAllowedOrigins(Collections.singletonList("*"));
//		config.setAllowedMethods(Collections.singletonList("*"));
//		config.setAllowedHeaders(Collections.singletonList("*"));
//		config.addExposedHeader("Authorization");
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return bean;
//	}

//	@Bean
//	CharacterEncodingFilter characterEncodingFilter() {
//		CharacterEncodingFilter filter = new CharacterEncodingFilter();
//		filter.setEncoding("UTF-8");
//		filter.setForceEncoding(true);
//		return filter;
//	}

}
