package tn.esprit.facturation.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.facturation.entities.Facture;
import tn.esprit.facturation.entities.LigneFacture;
import tn.esprit.facturation.repositories.FactureRepository;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@Service
public class FactureServiceImpl implements FactureService {
    
    private FactureRepository factureRepository;
    @Override
    public Facture create(Facture facture) {
        if (facture.getLignes()==null || facture.getLignes().isEmpty()) {
            throw new RuntimeException("La facture doit contenir au moins une ligne");
        }
        // Calcul
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

        return factureRepository.save(facture);
        
    }

    @Override
    public List<Facture> findAll() {
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
