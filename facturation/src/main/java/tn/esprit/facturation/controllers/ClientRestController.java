package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Client;
import tn.esprit.examen.nomPrenomClasseExamen.services.IServices;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("examen")
public class ClientRestController {
    private final IServices services;

    @PostMapping("/add-client")
    public Client add(@RequestBody Client client){
        return  services.add(client);
    }

    @GetMapping("/retrieve-all-clients")
    public List<Client> getClients() {
        List<Client> listClients = services.retrieveAllClients();
        return listClients;
    }
}
