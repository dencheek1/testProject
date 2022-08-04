package by.vsu.flight.service.impl;

import by.vsu.flight.model.City;
import by.vsu.flight.model.Flight;
import by.vsu.flight.repo.FlightRepository;
import by.vsu.flight.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl extends BaseServiceImpl<Flight, FlightRepository>
        implements FlightService {

    public FlightServiceImpl(FlightRepository repository) {
        super(repository);
    }

    @Override
    public List<Flight> findByCityFrom(City city) {
        return getRepository().findByCityFrom(city);
    }

    @Override
    public List<Flight> findByCityTo(City city) {
        return getRepository().findByCityTo(city);
    }
}
