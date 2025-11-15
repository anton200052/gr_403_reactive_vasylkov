package me.vasylkov.reactprogramming.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("booking_requests")
public class BookingRequest {
    @Id
    private Long id;

    @Column("client_id")
    private Long clientId;

    @Column("number_of_beds")
    private int numberOfBeds;

    @Column("apartment_class")
    private ApartmentClass apartmentClass;

    @Column("stay_start_time")
    private LocalDateTime stayStartTime;

    @Column("stay_end_time")
    private LocalDateTime stayEndTime;

    private BookingStatus status; // 'status' збігається з 'status' в SQL
}