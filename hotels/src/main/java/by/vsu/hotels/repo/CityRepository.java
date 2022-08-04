package by.vsu.hotels.repo;

import by.vsu.hotels.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Long> {

    List<City> findByCountryName(String countryName);
    List<City> findByName(String name);

}
