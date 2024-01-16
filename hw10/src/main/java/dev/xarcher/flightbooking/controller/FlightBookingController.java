package dev.xarcher.flightbooking.controller;

import dev.xarcher.flightbooking.model.dto.FlightBookingRequest;
import dev.xarcher.flightbooking.model.dto.FlightBookingResponse;
import dev.xarcher.flightbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class FlightBookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<FlightBookingResponse> booking(@RequestBody FlightBookingRequest request) {
        return ResponseEntity.ok(bookingService.booking(request));
    }

}
