package com.gb;

import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@ComponentScan(basePackages="com.gb")
public class GbApplication {

	public static void main(String[] args) {
		SpringApplication.run(GbApplication.class, args);
	}
}
