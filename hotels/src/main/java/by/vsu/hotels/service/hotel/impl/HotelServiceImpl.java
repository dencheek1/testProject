package by.vsu.hotels.service.hotel.impl;

import by.vsu.hotels.model.City;
import by.vsu.hotels.model.Hotel;
import by.vsu.hotels.repo.HotelRepository;
import by.vsu.hotels.service.BaseServiceImpl;
import by.vsu.hotels.service.hotel.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl extends BaseServiceImpl<Hotel, HotelRepository> implements HotelService {

    public HotelServiceImpl(HotelRepository repository) {
        super(repository);
    }

    @Override
    public Hotel findByName(String name) {
        return getRepository().findByName(name);
    }

    @Override
    public List<Hotel> findByCity(City city) {
        return getRepository().findByCity(city);
    }

    @Override
    public List<Hotel> findByTagName(String tagName) {
        List<Hotel> hotels = filterHotels(hotel -> hotel.getTags().stream().
                map(tag -> tag.getName()).
                filter(t -> t.equals(tagName))
                .count() > 0);
        return hotels;
    }

    @Override
    public List<Hotel> filterHotels(Predicate<Hotel> predicate) {
        return getRepository().findAll().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public Hotel update(Hotel hotel) {
        return getRepository().save(hotel);
    }
}
