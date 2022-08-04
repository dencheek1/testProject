package by.vsu.flight.dto;

import by.vsu.flight.model.Flight;
import by.vsu.flight.model.Tag;
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

    public Flight toFlight() {
        Flight flight = new Flight();
        flight.setArriveDate(arriveDate);
        flight.setLeaveDate(leaveDate);
        flight.setCityFrom(cityFrom.toCity());
        flight.setCityTo(cityTo.toCity());
        flight.setName(name);
        flight.setPlane(plane.toPlane());

        List<Tag> tagList = tags.stream()
                .map(t -> {
                    Tag tag = new Tag();
                    tag.setName(t);
                    return tag;
                })
                .toList();

        flight.setTags(tagList);
        return flight;
    }

    public static FlightDto fromFlight(Flight flight) {
        if(flight == null) {
            return null;
        }
        FlightDto flightDto = new FlightDto();
        flightDto.setName(flight.getName());
        flightDto.setCityFrom(CityDto.fromCity(flight.getCityFrom()));
        flightDto.setCityTo(CityDto.fromCity(flight.getCityTo()));
        flightDto.setArriveDate(flight.getArriveDate());
        flightDto.setLeaveDate(flight.getLeaveDate());
        flightDto.setPlane(PlaneDto.fromPlane(flight.getPlane()));
        flightDto.setTags(flight.getTags().stream().map(Tag::getName).toList());
        return flightDto;
    }
}
