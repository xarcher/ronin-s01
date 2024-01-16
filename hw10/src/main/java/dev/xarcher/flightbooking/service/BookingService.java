package dev.xarcher.flightbooking.service;

import dev.xarcher.flightbooking.exception.BusinessLogicException;
import dev.xarcher.flightbooking.model.dto.FlightBookingRequest;
import dev.xarcher.flightbooking.model.dto.FlightBookingResponse;
import dev.xarcher.flightbooking.model.entity.Booking;
import dev.xarcher.flightbooking.model.enumeration.BookingStatusType;
import dev.xarcher.flightbooking.model.enumeration.SeatStatus;
import dev.xarcher.flightbooking.repository.BookingRepository;
import dev.xarcher.flightbooking.util.RedisLockUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final SeatService seatService;

    private final FlightService flightService;

    private final RedisLockUtil redisLockUtil;

    private final BookingRepository bookingRepository;

    @Value("${booking.lock.expire-time:30000}")
    private long expireTime;

    @Value("${booking.lock.wait-time:10000}")
    private long waitTime;

    @SneakyThrows
    @Transactional
    public FlightBookingResponse booking(final FlightBookingRequest request) {
        var flight = flightService.getById(request.flightId());
        var seat = seatService.getById(request.seatId());

        Assert.isTrue(SeatStatus.AVAILABLE.equals(seat.getStatus()),
                "Seat id = %s is unavailable".formatted(seat.getId()));

        var clientLockIdRequest = request.clientRequestId();
        var lockKey = request.getLockKey();
        var isAcquireLock = redisLockUtil.tryLock(lockKey, clientLockIdRequest, expireTime, waitTime);
        if (isAcquireLock) {
            log.info("[{}] -> Booking request is acquired lock with key = {}, clientRequestId = {}",
                    Thread.currentThread().threadId(), lockKey, clientLockIdRequest);

            seat = seatService.getById(seat.getId());

            log.info("=> client request = {}, seat = {}", clientLockIdRequest, seat.toString());
            Assert.isTrue(SeatStatus.AVAILABLE.equals(seat.getStatus()),
                    "Seat id = %s is unavailable".formatted(seat.getId()));

            // simulation processing is 10s
            Thread.sleep(10000);

            var booking = bookingRepository.save(new Booking(clientLockIdRequest, flight.getId(), seat.getId(),
                    BookingStatusType.WAIT_PAYMENT));

            seat.setStatus(SeatStatus.UNAVAILABLE);
            seatService.update(seat);

            return FlightBookingResponse.of(booking, clientLockIdRequest);
        }

        throw new BusinessLogicException("Cannot booking. Please retry again!");
    }
}
