package com.kwan.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class BusinessApplication {

	public static void main(String[] args) {

		SpringApplication.run(BusinessApplication.class, args);
	}

}
