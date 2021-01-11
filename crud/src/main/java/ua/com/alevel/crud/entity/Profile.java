package ua.com.alevel.crud.entity;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.core.AbstractEntity;

@Getter
@Setter
public class Profile extends AbstractEntity {

    private String firstName;
    private String lastName;

    public Profile() {
        super();
    }
}
