package com.minicrm.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

enum CompanyStatus {
    NEUKUNDE,
    INTERESSIERT, 
    KUNDE,
    STAMMKUNDE,
    INAKTIV
}

@Entity // Zeigt an, dass Datenbank Tabelle ist
@Table(name = "companies") // benennt die Tabelle
public class Company {
    
    @Id // == Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private Long id;

    private String name;
    private String telephoneNumber;

    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    private BigDecimal umsatz;
    private Double standardrabatt;
    

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Note> notes;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<ContactPerson> contactPersons;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Quote> quotes;
    
    // --- Konstruktor ---
    public Company() {} 

    public Company(String name, String telephoneNumber, CompanyStatus status, Double standardrabatt) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.status = status;
        this.standardrabatt = standardrabatt;
        this.umsatz = BigDecimal.ZERO;
    }

    // --- Getter und Setter ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }

    public CompanyStatus getStatus() { return status; }
    public void setStatus(CompanyStatus status) { this.status = status; }

    public BigDecimal getUmsatz() { return umsatz; }
    public void setUmsatz(BigDecimal umsatz) { this.umsatz = umsatz; }

    public Double getStandardrabatt() { return standardrabatt; }
    public void setStandardrabatt(Double standardrabatt) { this.standardrabatt = standardrabatt; }

    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }

    public List<ContactPerson> getContactPersons() { return contactPersons; }
    public void setContactPersons(List<ContactPerson> contactPersons) 
            { this.contactPersons = contactPersons; }

    public List<Quote> getQuotes() { return quotes; }
    public void setQuotes(List<Quote> quotes) { this.quotes = quotes; }
}
