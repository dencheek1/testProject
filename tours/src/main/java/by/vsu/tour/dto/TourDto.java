package by.vsu.tour.dto;

import lombok.Data;

import java.util.List;

@Data
public class TourDto {

    private String name;
    private String flightName;
    private List<String> tags;
    private String city;
    private String countryName;

}
