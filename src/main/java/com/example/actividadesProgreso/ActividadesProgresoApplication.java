package com.example.actividadesProgreso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ActividadesProgresoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ActividadesProgresoApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ActividadesProgresoApplication.class);
    }
}