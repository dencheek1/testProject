package by.vsu.hotels.service.geo.impl;

import by.vsu.hotels.model.CurrencyType;
import by.vsu.hotels.repo.CurrencyTypeRepository;
import by.vsu.hotels.service.BaseServiceImpl;
import by.vsu.hotels.service.geo.CurrencyTypeService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyTypeServiceImpl extends BaseServiceImpl<CurrencyType, CurrencyTypeRepository>
        implements CurrencyTypeService {

    public CurrencyTypeServiceImpl(CurrencyTypeRepository repository) {
        super(repository);
    }

    @Override
    public CurrencyType findByName(String name) {
        return getRepository().findByName(name);
    }
}
