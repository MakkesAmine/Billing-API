package tn.esprit.facturation.services;

import tn.esprit.facturation.entities.Facture;

import java.time.LocalDate;
import java.util.List;

public interface FactureService {
    Facture create(Facture facture);
    Facture findById(Long id);
    List<Facture> allFactures();
    List<Facture> search(Long clientId, LocalDate date);
}
