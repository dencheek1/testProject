package by.vsu.tour.dto;

import lombok.Data;

import java.util.List;

@Data
public class TourPostDto {

    private Long flightId;
    private Long hotelId;
    private String name;
    private Long id;
    private String countryName;
    private List<String> tags;

}
