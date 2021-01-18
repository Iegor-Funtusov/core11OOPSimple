package ua.com.alevel.crud.entity;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.core.SecurityUser;

import java.util.Collections;

@Getter
@Setter
public class User extends SecurityUser {

    private Role role;

    public User() {
        super();
    }

    public User(Role role) {
        setRoleGroup(Collections.singletonList(role.name()));
    }
}
