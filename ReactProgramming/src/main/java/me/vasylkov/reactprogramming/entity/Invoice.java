package me.vasylkov.reactprogramming.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private Long id;
    private Long bookingRequestId; // ID заявки
    private Long clientId; // ID клієнта
    private Long roomId; // ID виділеного номера
    private BigDecimal amount; // Сума до сплати
    private LocalDate issueDate; // Дата виставлення
    private LocalDate dueDate; // Сплатити до
    private boolean isPaid; // Чи оплачено
}
