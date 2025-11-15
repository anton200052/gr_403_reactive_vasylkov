package me.vasylkov.reactprogramming.controller;

import me.vasylkov.reactprogramming.entity.Room;
import me.vasylkov.reactprogramming.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/rooms") // Всі запити до кімнат будуть починатися з /api/rooms
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // CREATE
    // POST http://localhost:8080/api/rooms
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Room> createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    // READ (All)
    // GET http://localhost:8080/api/rooms
    @GetMapping
    public Flux<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // READ (Available)
    // GET http://localhost:8080/api/rooms/available
    @GetMapping("/available")
    public Flux<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    // READ (By ID)
    // GET http://localhost:8080/api/rooms/1
    @GetMapping("/{id}")
    public Mono<Room> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    // UPDATE
    // PUT http://localhost:8080/api/rooms/1
    @PutMapping("/{id}")
    public Mono<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    // DELETE
    // DELETE http://localhost:8080/api/rooms/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoom(id);
    }
}