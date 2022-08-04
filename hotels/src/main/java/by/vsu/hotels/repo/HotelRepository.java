package by.vsu.hotels.repo;

import by.vsu.hotels.model.City;
import by.vsu.hotels.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

    Hotel findByName(String name);
    List<Hotel> findByCity(City city);

}
