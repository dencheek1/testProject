package by.vsu.tour.service.impl;

import by.vsu.tour.model.Tour;
import by.vsu.tour.repo.TourRepository;
import by.vsu.tour.service.TourService;
import org.springframework.stereotype.Service;

@Service
public class TourServiceImpl extends BaseServiceImpl<Tour, TourRepository> implements TourService {


    public TourServiceImpl(TourRepository repository) {
        super(repository);
    }

    @Override
    public Tour findByName(String name) {
        return getRepository().findByName(name);
    }
}
