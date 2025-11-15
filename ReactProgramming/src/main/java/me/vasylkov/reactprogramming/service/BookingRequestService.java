package me.vasylkov.reactprogramming.service;

import me.vasylkov.reactprogramming.entity.BookingRequest;
import me.vasylkov.reactprogramming.entity.BookingStatus;
import me.vasylkov.reactprogramming.repository.BookingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookingRequestService {

    private final BookingRequestRepository bookingRequestRepository;

    @Autowired
    public BookingRequestService(BookingRequestRepository bookingRequestRepository) {
        this.bookingRequestRepository = bookingRequestRepository;
    }

    public Mono<BookingRequest> createRequest(BookingRequest request) {
        return bookingRequestRepository.customInsertWithCast(
                request.getClientId(),
                request.getNumberOfBeds(),
                request.getApartmentClass().name(),
                request.getStayStartTime(),
                request.getStayEndTime(),
                request.getStatus().name()
        );
    }

    // READ (Get all)
    public Flux<BookingRequest> getAllRequests() {
        return bookingRequestRepository.findAll();
    }

    // READ (Get by ID)
    public Mono<BookingRequest> getRequestById(Long id) {
        return bookingRequestRepository.findById(id);
    }

    // READ (Custom finder by Status)
    public Flux<BookingRequest> getRequestsByStatus(BookingStatus status) {
        return bookingRequestRepository.findByStatus(status);
    }



    // UPDATE
    public Mono<BookingRequest> updateRequest(Long id, BookingRequest request) {
        return bookingRequestRepository.findById(id)
                .flatMap(existingRequest -> {
                    existingRequest.setClientId(request.getClientId());
                    existingRequest.setNumberOfBeds(request.getNumberOfBeds());
                    existingRequest.setApartmentClass(request.getApartmentClass());
                    existingRequest.setStayStartTime(request.getStayStartTime());
                    existingRequest.setStayEndTime(request.getStayEndTime());
                    existingRequest.setStatus(request.getStatus());
                    return bookingRequestRepository.save(existingRequest);
                });
    }

    // DELETE
    public Mono<Void> deleteRequest(Long id) {
        return bookingRequestRepository.deleteById(id);
    }
}
