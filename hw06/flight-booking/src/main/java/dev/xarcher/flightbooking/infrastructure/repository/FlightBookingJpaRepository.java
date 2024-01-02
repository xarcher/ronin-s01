package dev.xarcher.flightbooking.infrastructure.repository;

import dev.xarcher.flightbooking.domain.flightbooking.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightBookingJpaRepository extends JpaRepository<Long, FlightBooking> {
}
