package tn.esprit.facturation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.facturation.entities.Client;

@Repository

public interface ClientRepository extends JpaRepository<Client, Long> {
}

