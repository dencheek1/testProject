package by.vsu.hotels.service.hotel.impl;

import by.vsu.hotels.model.HotelServiceModel;
import by.vsu.hotels.repo.HotelServiceRepository;
import by.vsu.hotels.service.BaseServiceImpl;
import by.vsu.hotels.service.hotel.HotelServiceModelService;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceModelServiceImpl
        extends BaseServiceImpl<HotelServiceModel, HotelServiceRepository>
        implements HotelServiceModelService {


    public HotelServiceModelServiceImpl(HotelServiceRepository repository) {
        super(repository);
    }

    @Override
    public HotelServiceModel findByName(String name) {
        return getRepository().findByName(name);
    }
}
