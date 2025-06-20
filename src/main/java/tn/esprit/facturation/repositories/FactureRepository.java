package tn.esprit.facturation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.facturation.entities.Facture;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByClientId(Long clientId);
    List<Facture> findByDate(LocalDate date);
}

