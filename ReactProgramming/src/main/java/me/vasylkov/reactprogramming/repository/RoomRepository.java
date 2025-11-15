package me.vasylkov.reactprogramming.repository;

import me.vasylkov.reactprogramming.entity.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {

    // R2DBC автоматично створить запит "SELECT * FROM rooms WHERE is_available = $1"
    Flux<Room> findByIsAvailable(boolean isAvailable);
}