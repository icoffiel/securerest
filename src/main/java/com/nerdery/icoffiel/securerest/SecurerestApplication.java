package com.nerdery.icoffiel.securerest;

import com.nerdery.icoffiel.securerest.config.MyAppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MyAppProperties.class})
public class SecurerestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurerestApplication.class, args);
	}
}
