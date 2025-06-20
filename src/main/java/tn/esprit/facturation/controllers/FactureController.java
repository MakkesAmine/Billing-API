package tn.esprit.facturation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.facturation.entities.*;
import tn.esprit.facturation.services.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {
    private final FactureService factureService;
    // la creation d'une facture
    @PostMapping("/create")
    public ResponseEntity<Facture> createFacture (@RequestBody @Valid Facture facture) {
        try {
            if (facture.getLignes() != null) {
                for (LigneFacture ligne : facture.getLignes()) {
                    ligne.setFacture(facture);
                }
            }
            Facture savedFacture = factureService.create(facture);
            return ResponseEntity.ok(savedFacture);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/retreiveAllFactures")
    public List<Facture> getAllFactures() {
        return factureService.allFactures();
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
    public ResponseEntity<Resource> exportFacture(@PathVariable Long id) {
        try {
            Facture facture = factureService.findById(id);
            File tempFile=File.createTempFile("facture_"+id+"_",".json");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(tempFile, facture);

            InputStreamResource resource= new InputStreamResource(new FileInputStream(tempFile));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facture_"+id+".json");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(tempFile.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }

        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search")
    public List<Facture> searchFacture(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {
        return factureService.search(clientId, date);
    }
}
