package by.vsu.hotels.service.excursion;

import by.vsu.hotels.model.Excursion;
import by.vsu.hotels.service.BaseService;

public interface ExcursionService extends BaseService<Excursion> {

    Excursion findByName(String name);

}
