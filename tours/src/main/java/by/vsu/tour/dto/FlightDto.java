package by.vsu.tour.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FlightDto {

    private String name;

    private CityDto cityFrom;

    private CityDto cityTo;

    private PlaneDto plane;

    private Date leaveDate;

    private Date arriveDate;

    private List<String> tags;

}
