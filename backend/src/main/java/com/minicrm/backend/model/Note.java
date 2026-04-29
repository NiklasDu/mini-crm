package com.minicrm.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

enum NoteType {
    NOTIZ,
    BESUCHSBERICHT,
    ANRUF,
    EMAIL
}

@Entity
@Table(name = "notes")
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING) 
    private NoteType type;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false) // Foreign Key
    @JsonIgnore // verhindert Endlosschleife in Spring
    private Company company;

    // --- Konstruktoren ---
    public Note() {}

    public Note(String text, Company company, NoteType type) {
        this.text = text;
        this.company = company;
        this.type = type;
    }

    // --- Getter und Setter ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public NoteType getType() { return type; }
    public void setType(NoteType type) { this.type = type; } 
}
