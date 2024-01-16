package dev.xarcher.flightbooking.service;

import dev.xarcher.flightbooking.exception.ResourceNotFoundException;
import dev.xarcher.flightbooking.model.entity.Seat;
import dev.xarcher.flightbooking.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(final Seat seat) {
        seatRepository.saveAndFlush(seat);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Seat getById(final Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat with id = %s not found".formatted(id)));
    }
}
