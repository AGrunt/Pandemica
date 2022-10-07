package com.pandemica.app.pandemicapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.pandemica.app.pandemicapp.*"})
public class PandemicappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandemicappApplication.class, args);
	}

}
