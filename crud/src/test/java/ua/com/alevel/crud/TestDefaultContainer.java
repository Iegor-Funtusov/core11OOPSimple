package ua.com.alevel.crud;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import ua.com.alevel.core.ApplicationConfig;
import ua.com.alevel.core.CrudContainer;
import ua.com.alevel.crud.entity.Role;
import ua.com.alevel.crud.entity.User;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDefaultContainer {

    private final CrudContainer<User> defaultContainer = ApplicationConfig.getInstance().getContainer();
    private static final String LOGIN = "admin1";

    @Test
    public void create() {
        User user = getAdminUser();
        defaultContainer.create(user);
        List<User> users = defaultContainer.findAll();
        Assert.assertEquals(users.size(), 1);
    }

    @Test
    public void update() {
        User user = getAdminUser();
        defaultContainer.create(user);
        List<User> users = defaultContainer.findAll();
        Assert.assertEquals(users.size(), 1);
        user = users.get(0);
        user.setLogin(LOGIN);
        defaultContainer.update(user);
        user = defaultContainer.findById(1);
        Assert.assertEquals(user.getLogin(), LOGIN);
    }

    private User getAdminUser() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("nimda");
        user.setRole(Role.ADMIN);
        return user;
    }
}
