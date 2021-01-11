package ua.com.alevel.core;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class ApplicationConfig {

    private static ApplicationConfig instance;
    private final Reflections reflections;
    private Set<Class<? extends CrudContainer>> containers;

    private ApplicationConfig() {
        reflections = new Reflections("ua.com.alevel.core");
        containers = reflections.getSubTypesOf(CrudContainer.class);
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    public <AE extends AbstractEntity> CrudContainer<AE> getContainer() {
        Class<AE> implClass = null;
        for (Class<?> aClass : containers) {
            if (!aClass.isAnnotationPresent(Deprecated.class)) {
                implClass = (Class<AE>) aClass;
            }
        }
        if (implClass == null) {
            throw new RuntimeException("implementation is not present");
        }
        try {
            return (CrudContainer<AE>) implClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
