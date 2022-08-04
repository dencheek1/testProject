package by.vsu.tour.service.web;

import by.vsu.tour.dto.HotelDto;

import java.util.List;

public interface HotelService {

    List<HotelDto> getAll();
    HotelDto findById(Long id);
}
