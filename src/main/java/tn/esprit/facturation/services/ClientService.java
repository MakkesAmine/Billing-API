package tn.esprit.facturation.services;

import tn.esprit.facturation.entities.Client;

import java.util.List;

public interface ClientService {
    Client create(Client client);
    List<Client> findAll();
    Client findById(Long id);
}

