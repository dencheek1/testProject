package by.vsu.tour.repo;

import by.vsu.tour.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {

    Tour findByName(String name);

}
