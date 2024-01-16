package dev.xarcher.flightbooking.service;

import dev.xarcher.flightbooking.exception.ResourceNotFoundException;
import dev.xarcher.flightbooking.model.entity.Flight;
import dev.xarcher.flightbooking.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    @Transactional(readOnly = true)
    public Flight getById(final Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight with id = %s not found".formatted(id)));
    }
}
