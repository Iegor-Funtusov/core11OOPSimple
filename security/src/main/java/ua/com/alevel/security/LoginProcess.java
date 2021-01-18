package ua.com.alevel.security;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.core.ApplicationConfig;
import ua.com.alevel.core.CrudContainer;
import ua.com.alevel.core.SecurityUser;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class LoginProcess {

    private static final Map<String, Principal> claimsSessionMap = new HashMap<>();
    private static final CrudContainer<SecurityUser> container = ApplicationConfig.getInstance().getContainer();

    public static String login(String login, String password) {
        AtomicReference<String> token = new AtomicReference<>();
        claimsSessionMap.forEach((key, value) -> {
            if (value.getLogin().equals(login)) {
                token.set(key);
            }
        });

        Principal principal = new Principal();
        if (StringUtils.isBlank(token.get())) {
            String uuid = UUID.randomUUID().toString();
            SecurityUser securityUser = container.findAll().stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
            if (securityUser == null) {
                throw new RuntimeException("user not found");
            }
            if (!securityUser.getPassword().equals(password)) {
                throw new RuntimeException("password is invalid");
            }
            try {
                BeanUtils.copyProperties(principal, securityUser);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            claimsSessionMap.put(uuid, principal);
            token.set(uuid);
        }

        Session.getInstance().setCurrentPrincipal(principal);

        return token.get();
    }

    public static boolean isAuthenticated(String token) {
        return claimsSessionMap.get(token) != null;
    }
}
