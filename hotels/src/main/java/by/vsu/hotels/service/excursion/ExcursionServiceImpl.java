package by.vsu.hotels.service.excursion;

import by.vsu.hotels.model.Excursion;
import by.vsu.hotels.repo.ExcursionRepository;
import by.vsu.hotels.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ExcursionServiceImpl extends BaseServiceImpl<Excursion, ExcursionRepository> implements ExcursionService {

    public ExcursionServiceImpl(ExcursionRepository repository) {
        super(repository);
    }

    @Override
    public Excursion findByName(String name) {
        return getRepository().findByName(name);
    }
}
