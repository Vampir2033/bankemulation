package ru.dorogin.bankemulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.sql.Timestamp;

@SpringBootApplication()
public class BankemulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankemulationApplication.class, args);
	}

}
