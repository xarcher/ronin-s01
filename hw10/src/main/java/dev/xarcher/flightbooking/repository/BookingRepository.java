package dev.xarcher.flightbooking.repository;

import dev.xarcher.flightbooking.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
