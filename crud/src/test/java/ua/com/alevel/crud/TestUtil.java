package ua.com.alevel.crud;

import ua.com.alevel.crud.entity.Role;
import ua.com.alevel.crud.entity.User;

public class TestUtil {

    public static User getAdminUser() {
        User user = new User(Role.ADMIN);
        user.setLogin("admin");
        user.setPassword("nimda");
        return user;
    }
}
