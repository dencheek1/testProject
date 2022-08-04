package by.vsu.tour.service;

import by.vsu.tour.model.Tour;

public interface TourService extends BaseService<Tour> {

    Tour findByName(String name);

}
