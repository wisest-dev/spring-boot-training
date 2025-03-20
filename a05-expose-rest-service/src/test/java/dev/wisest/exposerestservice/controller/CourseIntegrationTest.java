package dev.wisest.exposerestservice.controller;

import dev.wisest.exposerestservice.model.Course;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getSoftwareEngineerCourse() {


        Course course = this.restTemplate.getForObject(
                "http://localhost:{port}/courses/{courseId}",
                Course.class,
                port, "SUCCEED_AS_SOFTWARE_ENGINEER");

        Assertions.assertThat(course).isNotNull();
        Assertions.assertThat(course.getAuthor()).isNotNull();
        Assertions.assertThat(course.getAuthor().getName()).isEqualTo("John Smith");
    }

    @Test
    public void getCourseThatDoesNotExist() {

        ResponseEntity<String> courseResponseEntity = this.restTemplate.getForEntity(
                "http://localhost:{port}/courses/{courseId}",
                String.class,
                port, "NOT_EXISTING_COURSE");

        Assertions.assertThat(courseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(courseResponseEntity.getBody()).isEqualTo("courseNotFoundHandler is here to tell you that: Could not find course NOT_EXISTING_COURSE");
    }

}


