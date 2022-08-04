package by.vsu.hotels.service;

import java.util.List;

public interface BaseService <T> {

    T findById(Long id);

    List<T> getAll();

    T save(T t);

    void delete(T t);
}
