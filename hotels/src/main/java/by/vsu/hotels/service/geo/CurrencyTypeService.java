package by.vsu.hotels.service.geo;

import by.vsu.hotels.model.CurrencyType;
import by.vsu.hotels.service.BaseService;

public interface CurrencyTypeService extends BaseService<CurrencyType> {

    CurrencyType findByName(String name);

}
