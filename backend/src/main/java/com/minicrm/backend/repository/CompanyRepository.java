package com.minicrm.backend.repository;

import com.minicrm.backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // Wenn leer, erstellt Spring aut. Methoden wie .findAll(), .findById(), .save() und .delete()
}
