package dev.xarcher.flightbooking.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(fluent = true)
public class FlightBookingRequest {
    @JsonProperty("flight_id")
    @NotNull
    private Long flightId;

    @JsonProperty("seat_id")
    @NotNull
    private Long seatId;

    private String clientRequestId = UUID.randomUUID().toString();

    public String getLockKey() {
        return "booking::%d::%d::lock".formatted(this.flightId, this.seatId);
    }
}
