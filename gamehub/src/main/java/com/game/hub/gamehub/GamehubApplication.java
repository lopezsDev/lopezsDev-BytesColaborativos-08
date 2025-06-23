package com.game.hub.gamehub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GamehubApplication {

	private static final Logger logger = LoggerFactory.getLogger(GamehubApplication.class);

	public static void main(String[] args) {
		logger.info("*************** Starting Gamehub App **************");
		SpringApplication.run(GamehubApplication.class, args);
	}

}
