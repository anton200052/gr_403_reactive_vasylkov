package me.vasylkov.reactprogramming.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("rooms") // Вказуємо назву таблиці в БД
public class Room {

    @Id // Вказуємо, що це Primary Key
    private Long id;

    @Column("room_number") // 'roomNumber' (Java) -> 'room_number' (SQL)
    private String roomNumber;

    @Column("number_of_beds")
    private int numberOfBeds;

    @Column("apartment_class")
    private ApartmentClass apartmentClass; // Spring R2DBC автоматично з'єднає Enum з ENUM-типом у Postgres

    @Column("is_available")
    private boolean isAvailable;

    @Column("price_per_night")
    private BigDecimal pricePerNight;
}