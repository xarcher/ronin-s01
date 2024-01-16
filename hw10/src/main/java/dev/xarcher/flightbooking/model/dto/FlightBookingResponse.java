package dev.xarcher.flightbooking.model.dto;

import dev.xarcher.flightbooking.model.entity.Booking;
import dev.xarcher.flightbooking.model.enumeration.BookingStatusType;

public record FlightBookingResponse(Long bookingId,
                                    Long flightId,
                                    Long seatId,
                                    BookingStatusType bookingStatus,
                                    String clientRequestId) {
    public static FlightBookingResponse of(final Booking booking, final String clientRequestId) {
        return new FlightBookingResponse(booking.getId(), booking.getFlightId(), booking.getSeatId(),
                booking.getStatus(), clientRequestId);
    }
}
