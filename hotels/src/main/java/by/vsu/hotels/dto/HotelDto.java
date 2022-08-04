package by.vsu.hotels.dto;

import by.vsu.hotels.model.Hotel;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class HotelDto {

    private String name;

    private String description;

    private String cityName;

    private String countryName;

    private List<String> services;

    private List<String> excursions;

    private List<String> tags;

    private int placesAvailable;


    public static HotelDto fromHotel(Hotel hotel) {
        if (hotel == null) return null;
        HotelDto hotelDto = new HotelDto();
        hotelDto.setName(hotel.getName());
        hotelDto.setDescription(hotel.getDescription());
        hotelDto.setCityName(hotel.getCity().getName());
        hotelDto.setPlacesAvailable(hotel.getPlacesAvailable());
        hotelDto.setServices(
                hotel.getServices().stream()
                        .map(s -> s.getName())
                        .collect(Collectors.toList())
        );
        hotelDto.setExcursions(
                hotel.getExcursions().stream()
                        .map(s -> s.getName())
                        .collect(Collectors.toList())
        );
        hotelDto.setTags(
                hotel.getTags().stream()
                        .map(s -> s.getName())
                        .collect(Collectors.toList())
        );
        hotelDto.setCountryName(hotel.getCity().getCountry().getName());
        return hotelDto;
    }

    public static List<HotelDto> fromHotel(List<Hotel> hotels) {
        if (hotels == null) return null;
        List<HotelDto> hotelDto = hotels.stream().map(HotelDto::fromHotel).collect(Collectors.toList());
        return hotelDto;
    }

}
