package ua.com.alevel.core;

import java.util.List;

public interface CrudContainer<E extends AbstractEntity> extends Container {

    List<E> findAll();
    E findById(int id);
    void create(E e);
    void update(E e);
    void delete(int id);
}
