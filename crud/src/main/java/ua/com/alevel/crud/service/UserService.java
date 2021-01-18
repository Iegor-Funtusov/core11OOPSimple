package ua.com.alevel.crud.service;

import ua.com.alevel.core.ApplicationConfig;
import ua.com.alevel.core.CrudContainer;
import ua.com.alevel.crud.entity.User;

import ua.com.alevel.security.HasRole;

import java.util.List;

public class UserService {

    private final CrudContainer<User> container = ApplicationConfig.getInstance().getContainer();

    @HasRole("ADMIN")
    public List<User> findAll() {
        return container.findAll();
    }

    @HasRole("ADMIN,CLIENT")
    public User findById(int id) {
        return container.findById(id);
    }

    public void create(User user) {
        container.create(user);
    }

    public void update(User user) {
        container.update(user);
    }

    public void delete(int id) {
        container.delete(id);
    }
}
