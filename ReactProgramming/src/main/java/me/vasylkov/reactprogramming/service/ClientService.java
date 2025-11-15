package me.vasylkov.reactprogramming.service;

import me.vasylkov.reactprogramming.entity.Client;
import me.vasylkov.reactprogramming.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // CREATE
    public Mono<Client> createClient(Client client) {
        return clientRepository.save(client);
    }

    // READ (Get all)
    public Flux<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // READ (Get by ID)
    public Mono<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // UPDATE
    public Mono<Client> updateClient(Long id, Client client) {
        return clientRepository.findById(id)
                .flatMap(existingClient -> {
                    existingClient.setFirstName(client.getFirstName());
                    existingClient.setLastName(client.getLastName());
                    existingClient.setAge(client.getAge());
                    return clientRepository.save(existingClient);
                });
    }

    // DELETE
    public Mono<Void> deleteClient(Long id) {
        return clientRepository.deleteById(id);
    }
}
