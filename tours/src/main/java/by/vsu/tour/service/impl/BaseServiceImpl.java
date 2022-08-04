package by.vsu.tour.service.impl;

import by.vsu.tour.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public abstract class BaseServiceImpl<T, R extends JpaRepository<T, Long>>
        implements BaseService<T> {

    private R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public T findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    public R getRepository() {
        return this.repository;
    }
}
