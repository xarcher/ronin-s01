package dev.xarcher.flightbooking.model.entity;

import dev.xarcher.flightbooking.model.enumeration.SeatStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private BigDecimal price;

    private Long flightId;
}
