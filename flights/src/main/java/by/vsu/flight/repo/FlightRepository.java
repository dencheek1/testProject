package by.vsu.flight.repo;

import by.vsu.flight.model.City;
import by.vsu.flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByCityFrom(City cityFrom);
    List<Flight> findByCityTo(City cityTo);
}
