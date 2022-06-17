package com.doordash.doordash;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoordashApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DoordashApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CustomerLowLevelDemo ctest = new CustomerLowLevelDemo();
		//ctest.connectDynamodb();

	}
}
