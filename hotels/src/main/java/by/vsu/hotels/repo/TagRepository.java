package by.vsu.hotels.repo;

import by.vsu.hotels.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

}
