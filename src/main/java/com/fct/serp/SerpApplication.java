package com.fct.serp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SerpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SerpApplication.class, args);
	}

}
