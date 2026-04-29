package com.minicrm.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

enum QuoteStatus {
    OFFEN,
    ANGENOMMEN,
    BESTELLT
}

@Entity
@Table(name = "quotes")
public class Quote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quoteNumber;
    private Double value;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime targetDate;

    @Enumerated(EnumType.STRING)
    private QuoteStatus status;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    // --- Konstruktoren ---
    public Quote() {}

    public Quote(Integer quoteNumber, Double value, String contents, LocalDateTime createdDate, 
        LocalDateTime targetDate, QuoteStatus status, User user, Company company) {
            this.quoteNumber = quoteNumber;
            this.value = value;
            this.contents = contents;
            this.createdDate = createdDate;
            this.targetDate = targetDate;
            this.status = status;
            this.user = user;
            this.company = company;
        }

    public Integer getQuoteNumber() { return quoteNumber; }
    public void setQuoteNumber(Integer quoteNumber) { this.quoteNumber = quoteNumber; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public String getContents() { return contents; }
    public void setContents(String contents) { this.contents = contents; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public LocalDateTime getTargetDate() { return targetDate; }
    public void setTargetDate(LocalDateTime targetDate) { this.targetDate = targetDate; }

    public QuoteStatus getStatus() { return status; }
    public void setStatus(QuoteStatus status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
