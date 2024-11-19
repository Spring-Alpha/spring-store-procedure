package com.example.springstoreprocedure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

@SpringBootApplication
public class SpringStoreProcedureApplication {

	@Autowired
	private ProcedureCreatorService procedureCreatorService;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringStoreProcedureApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			// Drop the procedure
			procedureCreatorService.deleteProcedure();
			System.out.println("Procedure drop operation triggered on application startup.");
			procedureCreatorService.createProcedure();
			System.out.println("Stored Procedure Created Successfully!");

			// Dummy data
			List<User> users = List.of(
					new User(1, "John Doe", "john.doe@example.com"),
					new User(2, "Jane Smith", "jane.smith@example.com"),
					new User(3, "Alice Brown", "alice.brown@example.com")
			);

			// Save all users
			userRepository.saveAll(users);
		};
	}
}
