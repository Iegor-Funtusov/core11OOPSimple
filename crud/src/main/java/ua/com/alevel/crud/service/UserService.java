package ua.com.alevel.crud.service;

import ua.com.alevel.core.ApplicationConfig;
import ua.com.alevel.core.CrudContainer;
import ua.com.alevel.crud.entity.User;

import java.util.List;

public class UserService {

    private final CrudContainer<User> container = ApplicationConfig.getInstance().getContainer();

    @HasRole("ADMIN")
    List<User> findAll() {
        return container.findAll();
    }

    @HasRole("ADMIN, CLIENT")
    User findById(int id) {
        return container.findById(id);
    }

    void create(User user) {
        container.create(user);
    }

    void update(User user) {
        container.update(user);
    }

    void delete(int id) {
        container.delete(id);
    }
}