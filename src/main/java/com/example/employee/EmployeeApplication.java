package com.example.employee;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.example.employee.*"})
public class EmployeeApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
}

@Configuration
class MyConfiguration {

	@Bean
	@Scope(value="singleton")
	public MyBean myBean() {
		return new MyBean();
	}

//	@Bean
//	public SecurityWebFilterChain securityWebFilterChain(
//			ServerHttpSecurity http) {
//		return http.authorizeExchange()
//				.pathMatchers("/actuator/**").permitAll()
//				.anyExchange().authenticated()
//				.and().build();
//	}

}

//record Employee (@Id Integer id, String firstName, String lastName, String metadata) {}
class MyBean {
	public MyBean() {
		System.out.println("MyBean instance created");
	}
	@PostConstruct
	private void init() {
		System.out.println("Verifying Resources - @PostConstruct");
	}
	@PreDestroy
	private void shutdown() {
		System.out.println("Shutdown All Resources - @PreDestroy");
	}
	public void close() {
		System.out.println("Closing All Resources");
	}
}



