package me.vasylkov.reactprogramming.repository;

import me.vasylkov.reactprogramming.entity.BookingRequest;
import me.vasylkov.reactprogramming.entity.BookingStatus;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookingRequestRepository extends ReactiveCrudRepository<BookingRequest, Long> {

    @Query("SELECT * FROM booking_requests WHERE status = CAST($1 AS booking_status_enum)")
    Flux<BookingRequest> findByStatus(BookingStatus status);

    @Query("INSERT INTO booking_requests (client_id, number_of_beds, apartment_class, stay_start_time, stay_end_time, status) " +
            "VALUES ($1, $2, CAST($3 AS apartment_class_enum), $4, $5, CAST($6 AS booking_status_enum)) " +
            "RETURNING *")
    Mono<BookingRequest> customInsertWithCast(
            Long clientId,
            Integer numberOfBeds,
            String apartmentClass, // Принимаем String
            java.time.LocalDateTime stayStartTime,
            java.time.LocalDateTime stayEndTime,
            String status          // Принимаем String
    );
}
