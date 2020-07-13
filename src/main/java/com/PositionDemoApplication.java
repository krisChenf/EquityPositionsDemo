package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
@EntityScan("com.equity.position.domain.entity")

public class PositionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PositionDemoApplication.class, args);
	}

}
