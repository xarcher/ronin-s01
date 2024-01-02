package dev.xarcher.flightbooking.infrastructure.repository.impl;

import dev.xarcher.flightbooking.domain.flightbooking.repository.FlightBookingRepository;
import dev.xarcher.flightbooking.infrastructure.repository.FlightBookingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightBookingRepositoryImpl implements FlightBookingRepository {
    private final FlightBookingJpaRepository flightBookingJpaRepository;

    // Do something
}
