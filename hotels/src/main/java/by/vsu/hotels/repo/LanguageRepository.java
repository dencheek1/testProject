package by.vsu.hotels.repo;

import by.vsu.hotels.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findByName(String name);

}
