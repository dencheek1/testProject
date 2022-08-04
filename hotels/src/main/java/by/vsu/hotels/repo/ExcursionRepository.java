package by.vsu.hotels.repo;

import by.vsu.hotels.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcursionRepository extends JpaRepository<Excursion,Long> {

    Excursion findByName(String name);
}
