package hu.webuni.hr.gpeter.model;

import java.time.LocalDateTime;

public class Employee {
    private Long id;
    private String name;
    private String post;
    private LocalDateTime entranceDate;

    public Employee() {
    }

    public Employee(Long id, String name, String post, LocalDateTime entranceDate) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.entranceDate = entranceDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LocalDateTime getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(LocalDateTime entranceDate) {
        this.entranceDate = entranceDate;
    }
}
