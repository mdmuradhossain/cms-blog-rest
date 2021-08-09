package io.murad.blog.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackages = {"io.murad.blog.rest.repository"})
public class CmsBlogRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsBlogRestApplication.class, args);
	}

}
