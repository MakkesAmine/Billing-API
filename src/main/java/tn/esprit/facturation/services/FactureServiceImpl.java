package tn.esprit.facturation.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.facturation.entities.*;
import tn.esprit.facturation.repositories.*;


import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@Service
public class FactureServiceImpl implements FactureService {
    
    private final FactureRepository factureRepository;
    private final ClientRepository clientRepository;
    @Override
    public Facture create(Facture facture) {
        if (facture.getLignes()==null || facture.getLignes().isEmpty()) {
            throw new RuntimeException("La facture doit contenir au moins une ligne");
        }
        if (facture.getClient()==null || facture.getClient().getId()==null) {
            throw new RuntimeException("Le client est obligatoire ");
        }

        Long clientId = facture.getClient().getId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Le client n'existe pas "));
        facture.setClient(client);

        double totalHT=0;
        double totalTVA=0;
        for (LigneFacture ligne : facture.getLignes()){
            double ht =ligne.getQte()*ligne.getPrixUnitHT();
            double tva = ht*(ligne.getTauxTVA().getValue()/100);
            totalHT+=ht;
            totalTVA+=tva;
            ligne.setFacture(facture);
        }
        facture.setTotalHT(totalHT);
        facture.setTotalTVA(totalTVA);
        facture.setTotalTTC(totalHT+totalTVA);
        facture.setDate(LocalDate.now());
        return factureRepository.save(facture);
        
    }

    @Override
    public List<Facture> allFactures() {
        return factureRepository.findAll();
    }

    @Override
    public Facture findById(Long id) {
        return factureRepository.findById(id).orElseThrow(()-> new RuntimeException("Le facture n'existe pas"));
    }

    @Override
    public List<Facture> search(Long clientId, LocalDate date){
        if (clientId != null) {
            return factureRepository.findByClientId(clientId);
        }
        else if (date != null) {
            return factureRepository.findByDate(date);
        }
        else {
            return factureRepository.findAll();
        }
    }
}
