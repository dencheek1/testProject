package by.vsu.tour.service.web;

import by.vsu.tour.dto.FlightDto;

import java.util.List;

public interface FlightDtoService {

    FlightDto findById(Long id);

    List<FlightDto> getAll();

    List<FlightDto> findByCityFrom(Long id);

    List<FlightDto> findByCityTo(Long id);

}
