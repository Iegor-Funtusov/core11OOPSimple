package ua.com.alevel.crud.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.core.AbstractEntity;

@Getter
@Setter
public class User extends AbstractEntity {

    private String login;
    private String password;
    private Role role;

    public User() {
        super();
    }
}
