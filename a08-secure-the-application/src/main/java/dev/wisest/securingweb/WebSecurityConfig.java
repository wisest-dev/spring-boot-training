package dev.wisest.securingweb;

/*-
 * #%L
 * Code accompanying video course "Java Spring Boot for Beginners"
 * %%
 * Copyright (C) 2025 Juhan Aasaru and Wisest.dev
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

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


	@Bean
	@Order(1)
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		http
				.securityMatcher("/api/**")
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/api/courses").hasRole("USER")
						.requestMatchers(antMatcher("/api/courses/{courseId}")).hasRole("USER")
						.requestMatchers(antMatcher("/api/courses/{courseId}/enrollments**")).hasRole("ADMIN")
				)
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/intro").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout(LogoutConfigurer::permitAll);

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("regular")
				.password("password")
				.roles("USER")
				.build();
		UserDetails userAdmin =
			 User.withDefaultPasswordEncoder()
				.username("regular-and-admin")
				.password("password")
				.roles("USER", "ADMIN")
				.build();

		UserDetails adminOnly =
			 User.withDefaultPasswordEncoder()
				.username("admin-only")
				.password("password")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, userAdmin, adminOnly);
	}

	@Bean
	public AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
	}

}
