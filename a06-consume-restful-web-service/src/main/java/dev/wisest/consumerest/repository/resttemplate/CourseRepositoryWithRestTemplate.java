package dev.wisest.consumerest.repository.resttemplate;

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

import dev.wisest.consumerest.model.Course;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.Tuple;

@Repository
public class CourseRepositoryWithRestTemplate {
    Logger log = LoggerFactory.getLogger(CourseRepositoryWithRestTemplate.class);

    @Resource
    private RestTemplate restTemplate;

    public Tuple<Course, Course> getTwoCourses(String secondCourseId) {

        Course xroadCourse = restTemplate.getForObject(
                "/courses/XROAD",
                Course.class);
        log.info("REST TEMPLATE :: XROAD course by: {}", xroadCourse.getAuthor());

        ResponseEntity<Course> secondCourseResponse = restTemplate.getForEntity(
                "/courses/{courseId}",
                Course.class,
                secondCourseId);

        Course secondCourse = secondCourseResponse.getBody();

        log.info("REST TEMPLATE :: {} COURSE: {}, status code was {}",
                secondCourseId, secondCourse, secondCourseResponse.getStatusCode());

        return new Tuple<>(xroadCourse, secondCourse);
    }




}
