package dev.xarcher.flightbooking.repository;

import dev.xarcher.flightbooking.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
