package tn.esprit.facturation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.facturation.entities.Client;
import tn.esprit.facturation.repositories.ClientRepository;
import tn.esprit.facturation.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {

        return ResponseEntity.ok(clientService.create(client));
    }

    @GetMapping("/retreiveAllClients")
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/retreiveClientById/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clientService.findById(id));
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

