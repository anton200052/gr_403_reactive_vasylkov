package me.vasylkov.reactprogramming.controller;

import me.vasylkov.reactprogramming.entity.BookingRequest;
import me.vasylkov.reactprogramming.entity.BookingStatus;
import me.vasylkov.reactprogramming.service.BookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/requests")
public class BookingRequestController {

    private final BookingRequestService bookingRequestService;

    @Autowired
    public BookingRequestController(BookingRequestService bookingRequestService) {
        this.bookingRequestService = bookingRequestService;
    }

    // CREATE
    // POST http://localhost:8080/api/requests
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookingRequest> createRequest(@RequestBody BookingRequest request) {
        return bookingRequestService.createRequest(request);
    }

    // READ (All)
    // GET http://localhost:8080/api/requests
    @GetMapping
    public Flux<BookingRequest> getAllRequests() {
        return bookingRequestService.getAllRequests();
    }

    // READ (By Status)
    // GET http://localhost:8080/api/requests/status?status=PENDING
    @GetMapping("/status")
    public Flux<BookingRequest> getRequestsByStatus(@RequestParam BookingStatus status) {
        return bookingRequestService.getRequestsByStatus(status);
    }

    // READ (By ID)
    // GET http://localhost:8080/api/requests/1
    @GetMapping("/{id}")
    public Mono<BookingRequest> getRequestById(@PathVariable Long id) {
        return bookingRequestService.getRequestById(id);
    }

    // UPDATE
    // PUT http://localhost:8080/api/requests/1
    @PutMapping("/{id}")
    public Mono<BookingRequest> updateRequest(@PathVariable Long id, @RequestBody BookingRequest request) {
        return bookingRequestService.updateRequest(id, request);
    }

    // DELETE
    // DELETE http://localhost:8080/api/requests/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteRequest(@PathVariable Long id) {
        return bookingRequestService.deleteRequest(id);
    }
}
