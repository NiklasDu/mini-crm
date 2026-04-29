package com.minicrm.backend.controller;

import com.minicrm.backend.model.Company;
import com.minicrm.backend.model.Note;
import com.minicrm.backend.model.ContactPerson;
import com.minicrm.backend.repository.CompanyRepository;
import com.minicrm.backend.repository.ContactPersonRepository;
import com.minicrm.backend.repository.NoteRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController // Sagt Spring, dass dies eine REST-API ist und gibt dadurch JSON zurück
@RequestMapping("/api/companies") // Basis Pfad für alle Routen hier drin
@CrossOrigin(origins = "*") // erlaubt React Frontend den Zugriff
public class CompanyController {
    
    private final ContactPersonRepository contactPersonRepository;
    private final CompanyRepository companyRepository;
    private final NoteRepository noteRepository;

    public CompanyController(CompanyRepository customerRepository, NoteRepository noteRepository, 
        ContactPersonRepository contactPersonRepository) {
        this.companyRepository = customerRepository;
        this.noteRepository = noteRepository;
        this.contactPersonRepository = contactPersonRepository;
    }

    // --- GET Endpunkte ---
    @GetMapping
    public List<Company> getAllCustomers() {
        return companyRepository.findAll(); 
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Firma mit der ID " + id + " wurde nicht gefunden"));
    }    

    // --- POST Endpunkte ---
    @PostMapping
    public Company createCustomer(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PostMapping("/{id}/notes")
    public Company addNoteToCompany(@PathVariable Long id, @RequestBody Note note) {
        return companyRepository.findById(id).map(company -> {
            note.setCompany(company);
            noteRepository.save(note);
            return company;
        }).orElseThrow(() -> new RuntimeException("Kunde mit ID " + id + " nicht gefunden"));
    }

    @PostMapping("/{id}/contactPersons")
    public Company addContactPersonToCompany(@PathVariable Long id, 
        @RequestBody ContactPerson contactPerson) {
        return companyRepository.findById(id).map(company -> {
            contactPerson.setCompany(company);
            contactPersonRepository.save(contactPerson);
            return company;
        }).orElseThrow(() -> new RuntimeException("Kunde mit ID " + id + " nicht gefunden"));
    }

    // --- PUT Endpunkte ---
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company updatedCompanyData) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Firma mit ID " + id + " nicht gefunden"));

        existingCompany.setName(updatedCompanyData.getName());
        existingCompany.setTelephoneNumber(updatedCompanyData.getTelephoneNumber());
        existingCompany.setStatus(updatedCompanyData.getStatus());
        existingCompany.setUmsatz(updatedCompanyData.getUmsatz());
        existingCompany.setStandardrabatt(updatedCompanyData.getStandardrabatt());

        return companyRepository.save(existingCompany);
    }
}
