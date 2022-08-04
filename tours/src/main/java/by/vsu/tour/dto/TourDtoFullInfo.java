package by.vsu.tour.dto;

import by.vsu.tour.model.Tag;
import lombok.Data;

import java.util.List;

@Data
public class TourDtoFullInfo {

    private String name;

    private FlightDto flight;

    private HotelDto hotel;
}
