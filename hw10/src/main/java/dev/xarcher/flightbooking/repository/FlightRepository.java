package dev.xarcher.flightbooking.repository;

import dev.xarcher.flightbooking.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
