package com.pictet.technologies.ezzine.fileorchestrator;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class FileOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileOrchestratorApplication.class, args);
	}

	@Bean
	public Server server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091").start();
	}
}
