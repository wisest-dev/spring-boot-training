package dev.wisest.consumerest.example03;

/*-
 * #%L
 * Code accompanying video course "Java Spring Boot for Beginners"
 * %%
 * Copyright (C) 2024 - 2025 Juhan Aasaru and Wisest.dev
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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;

@Configuration
@Profile("example03")
public class ConfigExample03 {

    @Value("${enrollmentWebservice.url}")
    String enrollmentWebserviceUrl;

    @Bean
    @Primary
    public RestClient restClient2() {
        return RestClient.builder()
                .baseUrl(enrollmentWebserviceUrl)
                .requestInterceptor(new LoggingRequestInterceptor())
                .build();
    }
}
