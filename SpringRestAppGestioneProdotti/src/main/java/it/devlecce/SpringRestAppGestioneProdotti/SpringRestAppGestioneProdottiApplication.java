package it.devlecce.SpringRestAppGestioneProdotti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestAppGestioneProdottiApplication {
	private Logger logger = LoggerFactory.getLogger(SpringRestAppGestioneProdottiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringRestAppGestioneProdottiApplication.class, args);
	}


}
