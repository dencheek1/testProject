package by.vsu.hotels.repo;

import by.vsu.hotels.model.HotelServiceModel;
import by.vsu.hotels.service.hotel.HotelServiceModelService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServiceRepository extends JpaRepository<HotelServiceModel,Long> {

    HotelServiceModel findByName(String name);

}
