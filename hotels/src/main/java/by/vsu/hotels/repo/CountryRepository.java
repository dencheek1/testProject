package by.vsu.hotels.repo;

import by.vsu.hotels.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {

    Country findByName(String name);

}
