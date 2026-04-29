package com.minicrm.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

enum TaskStatus {
    ERLEDIGT,
    IN_BEARBEITUNG,
    OFFEN
}

@Entity
@Table(name = "tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String header;
    private String text;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    // --- Konstruktoren ---
    public Task() {}

    public Task(String header, String text, TaskStatus status, User user) {
        this.header = header;
        this.text = text;
        this.status = status;
        this.user = user;
    }

    // --- Getter und Setter ---
    public String getHeader() { return header; }
    public void setHeader(String header) { this.header = header; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
