package by.vsu.tour.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelDto {

    private String name;

    private String description;

    private String cityName;

    private List<String> services;

    private List<String> excursions;

    private List<String> tags;

    private String countryName;

    private int placesAvailable;


}
