package com.alexeiboriskin.repository;

import com.alexeiboriskin.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Flight entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<List<Flight>> findByDepartureBetween(LocalTime from, LocalTime to);

}
