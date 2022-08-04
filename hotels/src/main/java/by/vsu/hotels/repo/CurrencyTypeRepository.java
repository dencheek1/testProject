package by.vsu.hotels.repo;

import by.vsu.hotels.model.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyTypeRepository extends JpaRepository<CurrencyType,Long> {

    CurrencyType findByName(String name);
}
