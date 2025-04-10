package dev.wisest.consumerest.repository.restclient;

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

import dev.wisest.consumerest.model.Enrollment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Repository
public class EnrollmentRepositoryWithRestClient {
    Logger log = LoggerFactory.getLogger(EnrollmentRepositoryWithRestClient.class);

    private final RestClient restClient;

    public EnrollmentRepositoryWithRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public Enrollment addEnrollment(Enrollment enrollmentToAdd) {
        String courseId = enrollmentToAdd.getCourse().getCourseId();
        log.info("REST CLIENT :: About to create an enrollment in course {}", courseId);

        ResponseEntity<Enrollment> addedEnrollmentResponse = restClient
                .post()
                .uri("/courses/{courseId}/enrollments", courseId)
                .body(enrollmentToAdd)
                .retrieve()
                .toEntity(Enrollment.class);

        return addedEnrollmentResponse.getBody();
    }

    public void deleteEnrollment(String courseId, UUID enrollmentId) {

        ResponseEntity<Void> deleteResult = restClient.delete()
                .uri("/courses/{courseId}/enrollments/{enrollmentId}", courseId, enrollmentId)
                .retrieve()
                .toBodilessEntity();

        log.debug("Status code was returned: {}", deleteResult.getStatusCode());


    }

}
