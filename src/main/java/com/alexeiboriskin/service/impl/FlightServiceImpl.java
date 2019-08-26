package com.alexeiboriskin.service.impl;

import com.alexeiboriskin.domain.Flight;
import com.alexeiboriskin.repository.FlightRepository;
import com.alexeiboriskin.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Service Implementation for managing Flight.
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    private static final int HOURS_RANGE = 5;
    private final Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Save a flight.
     *
     * @param flight the entity to save
     * @return the persisted entity
     */
    @Override
    public Flight save(Flight flight) {
        log.debug("Request to save Flight : {}", flight);
        return flightRepository.save(flight);
    }

    /**
     * Get all the flights.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Flight> findAll() {
        log.debug("Request to get all Flights");
        return flightRepository.findAll();
    }


    /**
     * Get one flight by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Flight> findOne(Long id) {
        log.debug("Request to get Flight : {}", id);
        return flightRepository.findById(id);
    }

    @Override
    public Optional<List<Flight>> findByDepartureBetween(LocalTime from, LocalTime to) {
        log.debug("Request to get Flights from : {} to : {}", from, to);
        return flightRepository.findByDepartureBetween(from, to);
    }

    /**
     * Delete the flight by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Flight : {}", id);
        flightRepository.deleteById(id);
    }

    @Override
    public Optional<List<Flight>> searchDeparture(String departure) {
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("h:mma")
            .toFormatter(Locale.US);

        final LocalTime departureTime = LocalTime.parse(departure, formatter);

        return flightRepository.findByDepartureBetween(departureTime.minusHours(HOURS_RANGE),
            departureTime.plusHours(HOURS_RANGE));
    }
}
