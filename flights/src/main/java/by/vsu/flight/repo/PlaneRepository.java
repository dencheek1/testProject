package by.vsu.flight.repo;

import by.vsu.flight.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    Plane findByPlaneModel(String planeModel);
}
