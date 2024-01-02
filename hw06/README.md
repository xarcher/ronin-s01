# Homework 06

## Requirements

### Build a codebase

1. Apply Clean Architecture + DDD
2. case: Flight Booking
3. Implement in high-level, not detail

## Explain

### Workflow

```plaintext
.
└── src
    ├── main
    │ ├── java
    │ │ └── dev
    │ │     └── xarcher
    │ │         └── flightbooking
    │ │             ├── application
    │ │             │ ├── aop
    │ │             │ │ └── ControllerLoggingAspect.java
    │ │             │ └── endpoint
    │ │             │     └── rest
    │ │             │         └── FlightBookingController.java
    │ │             ├── domain
    │ │             │ ├── flightbooking
    │ │             │ │ ├── dto
    │ │             │ │ │ ├── FlightBookingCreateRequest.java
    │ │             │ │ │ └── FlightBookingCreateResponse.java
    │ │             │ │ ├── entity
    │ │             │ │ │ └── FlightBooking.java
    │ │             │ │ ├── repository
    │ │             │ │ │ └── FlightBookingRepository.java
    │ │             │ │ └── service
    │ │             │ │     ├── FlightBookingService.java
    │ │             │ │     └── impl
    │ │             │ │         └── FlightBookingServiceImpl.java
    │ │             │ ├── payment
    │ │             │ │ ├── dto
    │ │             │ │ │ ├── FlightBookingPaymentRequest.java
    │ │             │ │ │ └── FlightBookingPaymentResponse.java
    │ │             │ │ └── service
    │ │             │ │     ├── impl
    │ │             │ │     │ └── PaymentServiceImpl.java
    │ │             │ │     └── PaymentService.java
    │ │             │ └── ticket
    │ │             │     ├── entity
    │ │             │     │ └── Ticket.java
    │ │             │     ├── repository
    │ │             │     │ └── TicketRepository.java
    │ │             │     └── service
    │ │             │         ├── impl
    │ │             │         │ └── TicketServiceImpl.java
    │ │             │         └── TicketService.java
    │ │             ├── FlightBookingApplication.java
    │ │             └── infrastructure
    │ │                 ├── aop
    │ │                 │ └── MessagingLoggingAspect.java
    │ │                 ├── config
    │ │                 │ ├── ExecuteConfiguration.java
    │ │                 │ └── properties
    │ │                 │     └── ExecutorProperties.java
    │ │                 ├── messaging
    │ │                 │ └── TicketEmailProducer.java
    │ │                 ├── repository
    │ │                 │ ├── FlightBookingJpaRepository.java
    │ │                 │ ├── impl
    │ │                 │ │ ├── FlightBookingRepositoryImpl.java
    │ │                 │ │ └── TicketRepositoryImpl.java
    │ │                 │ └── TicketJpaRepository.java
    │ │                 ├── rest
    │ │                 │ └── PaymentGateway.java
    │ │                 └── service
    │ │                     ├── EmailService.java
    │ │                     └── impl
    │ │                         └── EmailServiceImpl.java
    │ └── resources
    │     ├── application.properties
    │     ├── static
    │     └── templates
    └── test
        └── java
            └── dev
                └── xarcher
                    └── flightbooking
                        └── FlightBookingApplicationTests.java

```
