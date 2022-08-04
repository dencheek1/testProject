package by.vsu.flight.service.impl;

import by.vsu.flight.model.Plane;
import by.vsu.flight.repo.PlaneRepository;
import by.vsu.flight.service.PlaneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaneServiceImpl extends BaseServiceImpl<Plane, PlaneRepository> implements PlaneService {

    public PlaneServiceImpl(PlaneRepository repository) {
        super(repository);
    }

    @Override
    public Plane findByModel(String model) {
        return getRepository().findByPlaneModel(model);
    }

    @Override
    public List<Plane> findWithMinCapacity(int capacity) {
        List<Plane> planes = getAll().stream().filter(plane -> plane.getCapacity() >= capacity).collect(Collectors.toList());
        return planes;
    }
}
