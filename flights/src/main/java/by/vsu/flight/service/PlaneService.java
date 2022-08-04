package by.vsu.flight.service;

import by.vsu.flight.model.Plane;

import java.util.List;

public interface PlaneService extends BaseService<Plane> {
    Plane findByModel(String model);
    List<Plane> findWithMinCapacity(int capacity);
}
