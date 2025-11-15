package me.vasylkov.reactprogramming.service;

import me.vasylkov.reactprogramming.entity.Room;
import me.vasylkov.reactprogramming.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // CREATE
    public Mono<Room> createRoom(Room room) {
        return roomRepository.save(room);
    }

    // READ (Get all)
    public Flux<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // READ (Get by ID)
    public Mono<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    // READ (Custom finder)
    public Flux<Room> getAvailableRooms() {
        return roomRepository.findByIsAvailable(true);
    }

    // UPDATE
    public Mono<Room> updateRoom(Long id, Room room) {
        return roomRepository.findById(id)
                .flatMap(existingRoom -> {
                    existingRoom.setRoomNumber(room.getRoomNumber());
                    existingRoom.setNumberOfBeds(room.getNumberOfBeds());
                    existingRoom.setApartmentClass(room.getApartmentClass());
                    existingRoom.setAvailable(room.isAvailable());
                    existingRoom.setPricePerNight(room.getPricePerNight());
                    return roomRepository.save(existingRoom);
                });
    }

    // DELETE
    public Mono<Void> deleteRoom(Long id) {
        return roomRepository.deleteById(id);
    }
}