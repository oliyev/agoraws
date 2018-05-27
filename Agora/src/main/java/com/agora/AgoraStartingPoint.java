package com.agora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class AgoraStartingPoint extends SpringBootServletInitializer {
	
	//creates the servlet container and everything -- amazing
	public static void main(String[] args) {
		SpringApplication.run(AgoraStartingPoint.class , args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AgoraStartingPoint.class);
	}
}
