package com.accenture.idm.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.accenture.idm.model.User;
import com.accenture.idm.repository.UserRepository;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		return strings -> userRepository.save(new User("Hiran fernando", "admin", "123", "hiran@gmail.com", roles));
	}

}
