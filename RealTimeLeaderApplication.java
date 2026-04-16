package com.LeaderBoard.Real_timeLeader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealTimeLeaderApplication {
	public static void main(String[] args) { // Add the 's' here
		// This line starts the entire Spring Boot framework
		SpringApplication.run(RealTimeLeaderApplication.class, args);

		System.out.println("🚀 Leaderboard Backend is running on http://localhost:8081");
		System.out.println("📊 Access H2 Database Console at http://localhost:8081/h2-console");
	}
}