package dev.wisest.servewebcontent;

import java.io.Serializable;

public class Person implements Serializable {

    public Person() {
    }

    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
