package me.vasylkov.reactprogramming.controller;

import me.vasylkov.reactprogramming.entity.Client;
import me.vasylkov.reactprogramming.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // CREATE
    // POST http://localhost:8080/api/clients
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Client> createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // READ (All)
    // GET http://localhost:8080/api/clients
    @GetMapping
    public Flux<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // READ (By ID)
    // GET http://localhost:8080/api/clients/1
    @GetMapping("/{id}")
    public Mono<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    // UPDATE
    // PUT http://localhost:8080/api/clients/1
    @PutMapping("/{id}")
    public Mono<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    // DELETE
    // DELETE http://localhost:8080/api/clients/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteClient(@PathVariable Long id) {
        return clientService.deleteClient(id);
    }
}
