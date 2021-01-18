package ua.com.alevel.security;

import java.util.List;

public class Principal {

    private String login;
    private String password;
    private List<String> roleGroup;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(List<String> roleGroup) {
        this.roleGroup = roleGroup;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roleGroup=" + roleGroup +
                '}';
    }
}
