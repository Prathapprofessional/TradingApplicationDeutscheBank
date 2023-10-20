package com.db.test.TradingApplicationDeutscheBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DBTradingApplication {
		public static void main (String[]args){
		SpringApplication.run(DBTradingApplication.class, args);
		System.out.println("Welcome to Deutsche Bank Trading Application");
	}
}
