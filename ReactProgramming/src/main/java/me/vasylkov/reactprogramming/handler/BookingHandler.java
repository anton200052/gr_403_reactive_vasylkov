package me.vasylkov.reactprogramming.handler;

import me.vasylkov.reactprogramming.entity.ApartmentClass;
import me.vasylkov.reactprogramming.entity.BookingRequest;
import me.vasylkov.reactprogramming.entity.BookingStatus;
import me.vasylkov.reactprogramming.entity.Room;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class BookingHandler {

    // Метод для головної сторінки (повертає простий текст)
    public Mono<ServerResponse> home(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Головна сторінка системи бронювання!"));
    }

    // Метод, що повертає одну кімнату (демонстрація Mono)
    public Mono<ServerResponse> getRoomInfo(ServerRequest request) {
        Mono<Room> room = Mono.just(
                new Room(1L, "101A", 2, ApartmentClass.STANDARD, true, new BigDecimal("150.00"))
        );

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(room, Room.class);
    }

    // Метод, що повертає список заявок (демонстрація Flux та параметрів)
    public Mono<ServerResponse> getPendingRequests(ServerRequest request) {
        String start = request
                .queryParam("start")
                .orElse("0");

        Flux<BookingRequest> requests = Flux.just(
                        new BookingRequest(1L, 101L, 2, ApartmentClass.STANDARD, LocalDateTime.now(), LocalDateTime.now().plusDays(3), BookingStatus.PENDING),
                        new BookingRequest(2L, 102L, 1, ApartmentClass.ECONOMY, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), BookingStatus.PENDING),
                        new BookingRequest(3L, 103L, 3, ApartmentClass.BUSINESS, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(5), BookingStatus.APPROVED)
                )
                .skip(Long.valueOf(start))
                .take(2);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(requests, BookingRequest.class);
    }
}

