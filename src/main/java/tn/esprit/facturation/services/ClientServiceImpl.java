package tn.esprit.facturation.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.facturation.entities.Client;
import tn.esprit.facturation.repositories.ClientRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        client.setId(null);
        return clientRepository.save(client);
    }
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

}

