package tn.esprit.facturation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.facturation.entities.Facture;
import tn.esprit.facturation.entities.LigneFacture;
import tn.esprit.facturation.repositories.ClientRepository;
import tn.esprit.facturation.repositories.FactureRepository;
import tn.esprit.facturation.services.FactureService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {
    private final FactureService factureService;
    // la creation d'une facture
    @PostMapping("/create")
    public ResponseEntity<Facture> createFacture (@RequestBody Facture facture) {
        try {
            return ResponseEntity.ok(factureService.create(facture));
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/retreiveAllFactures")
    public List<Facture> getAllFactures() {
        return factureService.findAll();
    }
    @GetMapping("retreiveFactureById/{id}")
    public ResponseEntity<Facture> getFacture(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(factureService.findById(id));
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/export/{id}")
    public ResponseEntity<Facture> exportFacture(@PathVariable Long id) {
        return getFacture(id);
    }
    @GetMapping("/search")
    public List<Facture> searchFacture(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {
        return factureService.search(clientId, date);
    }
}
