package com.bandits.api.recipe_app_api;

import com.bandits.api.recipe_app_api.model.AppUser;
import com.bandits.api.recipe_app_api.model.Role;
import com.bandits.api.recipe_app_api.repository.RoleRepository;
import com.bandits.api.recipe_app_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RecipeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry reg) {
				reg.addMapping("/**").allowedOrigins("http://localhost:5173");
			}
		};
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if(roleRepository.findByAuthority("admin").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("admin"));
			roleRepository.save(new Role("user"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			AppUser admin = new AppUser(1,"admin", "azeez@yahoo.com",passwordEncoder.encode("pass"),roles);
			userRepository.save(admin);
		};
	}
}
