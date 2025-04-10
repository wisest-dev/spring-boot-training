package dev.wisest.securingweb.api;

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

import dev.wisest.securingweb.model.Course;
import dev.wisest.securingweb.model.Enrollment;
import dev.wisest.securingweb.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EnrollmentApiController {


    @GetMapping("/api/courses/{courseId}/enrollments")
    Iterable<Enrollment> allOfCourse(@PathVariable String courseId) {

        Person mickey = new Person("Mickey Mouse");
        mickey.setPersonId(4L);

        Course courseDTO = new Course(courseId);

        Enrollment enrollment = new Enrollment(mickey, courseDTO, LocalDate.of(2025, 1, 13));

        return List.of(enrollment);
    }


}
