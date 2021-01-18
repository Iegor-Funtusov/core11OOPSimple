package ua.com.alevel.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SecurityUser extends AbstractEntity {

    private String login;
    private String password;
    private List<String> roleGroup;
}
