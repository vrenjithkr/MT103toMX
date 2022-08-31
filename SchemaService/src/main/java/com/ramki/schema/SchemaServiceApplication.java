package com.ramki.schema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = { "com.ramki" })
@ComponentScan("com.ramki")
public class SchemaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchemaServiceApplication.class, args);
	}

}
