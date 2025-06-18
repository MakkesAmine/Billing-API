package tn.esprit.facturation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.facturation.entities.LigneFacture;

@Repository

public interface LigneFactureRepository extends JpaRepository<LigneFacture, Long> {

}
