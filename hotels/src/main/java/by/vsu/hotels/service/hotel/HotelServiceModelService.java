package by.vsu.hotels.service.hotel;

import by.vsu.hotels.model.HotelServiceModel;
import by.vsu.hotels.service.BaseService;

public interface HotelServiceModelService extends BaseService<HotelServiceModel> {

    HotelServiceModel findByName(String name);

}
