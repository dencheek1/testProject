package by.vsu.hotels.service.hotel;

import by.vsu.hotels.model.City;
import by.vsu.hotels.model.Hotel;
import by.vsu.hotels.service.BaseService;

import java.util.List;
import java.util.function.Predicate;

public interface HotelService extends BaseService<Hotel> {

    Hotel findByName(String name);
    List<Hotel> findByCity(City city);
    List<Hotel> findByTagName(String tagName);
    List<Hotel> filterHotels(Predicate<Hotel> predicate);
    Hotel update(Hotel hotel);

}
