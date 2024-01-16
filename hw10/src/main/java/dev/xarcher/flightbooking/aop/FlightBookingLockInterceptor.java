package dev.xarcher.flightbooking.aop;

import dev.xarcher.flightbooking.model.dto.FlightBookingRequest;
import dev.xarcher.flightbooking.model.dto.FlightBookingResponse;
import dev.xarcher.flightbooking.util.RedisLockUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class FlightBookingLockInterceptor {
    private final RedisLockUtil redisLockUtil;

    @Around("execution (* dev.xarcher.flightbooking.service.BookingService.booking(..))")
    public Object unlockBooking(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=> Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

        var request = (FlightBookingRequest) joinPoint.getArgs()[0];
        try {
            var response = (FlightBookingResponse) joinPoint.proceed();
            log.info("<= Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), response);

            return response;
        } catch (Exception e) {
            log.info("=> Ex: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        } finally {
            var unlock = redisLockUtil.unlock(request.getLockKey(), request.clientRequestId());
            log.info("unlock clientRequestId = {} is {}", request.clientRequestId(), unlock);
        }
    }
}
