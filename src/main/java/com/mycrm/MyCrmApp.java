package com.mycrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.mycrm")
@SpringBootApplication
public class MyCrmApp {
	public static void main(String[] args) {
		SpringApplication.run(MyCrmApp.class, args);

	}
}
