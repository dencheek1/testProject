package by.vsu.flight.service;

import by.vsu.flight.model.City;
import by.vsu.flight.model.Flight;

import java.util.List;

public interface FlightService extends BaseService<Flight> {

    List<Flight> findByCityFrom(City city);
    List<Flight> findByCityTo(City city);

}
