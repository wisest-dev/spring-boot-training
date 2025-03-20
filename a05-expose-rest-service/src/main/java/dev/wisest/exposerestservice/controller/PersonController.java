package dev.wisest.exposerestservice.controller;

import dev.wisest.exposerestservice.model.Person;
import dev.wisest.exposerestservice.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PersonController {

    private final PersonRepository personRepository;

    PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    Iterable<Person> getPerson(@RequestParam String name) {
        return personRepository.findByNameIgnoreCase(name);
    }

}
