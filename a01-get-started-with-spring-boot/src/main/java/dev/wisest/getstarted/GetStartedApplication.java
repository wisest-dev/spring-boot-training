package dev.wisest.getstarted;

/*-
 * #%L
 * Code accompanying video course "Become a Java/SpringBoot Backend Developer"
 * %%
 * Copyright (C) 2023 Wisest.dev and Juhan Aasaru
 * %%
 * The source code (including test code) in this repository is licensed under a
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Licence.
 * 
 * Attribution-NonCommercial-NoDerivatives 4.0 International Licence is a standard
 * form licence agreement that does not permit any commercial use or derivatives
 * of the original work. Under this licence: you may only distribute a verbatim
 * copy of the work. If you remix, transform, or build upon the work in any way then
 * you are not allowed to publish nor distribute modified material.
 * You must give appropriate credit and provide a link to the licence.
 * 
 * The full licence terms are available from
 * <http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode>
 * 
 * All the code (including tests) contained herein should be attributed as:
 * © Juhan Aasaru https://Wisest.dev
 * #L%
 */

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
public class GetStartedApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetStartedApplication.class, args);
	}

	@Bean
	public CommandLineRunner beanNamePrinter(ApplicationContext ctx) {
		System.out.println("The method that returns a bean was called.");
		return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("Let's list the beans provided by Spring Boot:");

                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    System.out.println(beanName);
                }
            }
        };
	}

}
