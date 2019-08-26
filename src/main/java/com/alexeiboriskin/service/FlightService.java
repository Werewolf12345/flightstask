package com.alexeiboriskin.service;

import com.alexeiboriskin.domain.Flight;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Flight.
 */
public interface FlightService {

    /**
     * Save a flight.
     *
     * @param flight the entity to save
     * @return the persisted entity
     */
    Flight save(Flight flight);

    /**
     * Get all the flights.
     *
     * @return the list of entities
     */
    List<Flight> findAll();


    /**
     * Get the "id" flight.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Flight> findOne(Long id);

    Optional<List<Flight>> findByDepartureBetween(LocalTime from, LocalTime to);

    /**
     * Delete the "id" flight.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all flights in a range of +-5 hours of departure
     *
     * @param departure the String representation of flight departure time in h:mma format
     */
    Optional<List<Flight>> searchDeparture(String departure);
}
