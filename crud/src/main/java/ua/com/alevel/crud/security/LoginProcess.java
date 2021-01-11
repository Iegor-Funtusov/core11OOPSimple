package ua.com.alevel.crud.security;

import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.core.ApplicationConfig;
import ua.com.alevel.core.CrudContainer;
import ua.com.alevel.crud.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class LoginProcess {

    private final Map<String, User> claimsSessionMap = new HashMap<>();
    private final CrudContainer<User> container = ApplicationConfig.getInstance().getContainer();

    public String login(String login, String password) {
        AtomicReference<String> token = new AtomicReference<>();
        claimsSessionMap.forEach((key, value) -> {
            if (value.getLogin().equals(login)) {
                token.set(key);
            }
        });

        if (StringUtils.isBlank(token.get())) {
            String uuidToken = UUID.randomUUID().toString();
            User user = container.findAll().stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
            if (user == null) {
                throw new RuntimeException("user not found");
            }
            if (!user.getPassword().equals(password)) {
                throw new RuntimeException("password is invalid");
            }
            claimsSessionMap.put(uuidToken, user);
            return uuidToken;
        }

        return token.get();
    }

    public boolean isAuthenticated(String token) {
        return claimsSessionMap.get(token) != null;
    }
}
