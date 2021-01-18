package ua.com.alevel.security;

public class Session {

    private static Session instance;

    private Principal currentPrincipal;

    private Session() { }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Principal getCurrentPrincipal() {
        return currentPrincipal;
    }

    public void setCurrentPrincipal(Principal currentPrincipal) {
        this.currentPrincipal = currentPrincipal;
    }
}
