package ua.com.alevel.core;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationConfig {

    private static ApplicationConfig instance;
    private final Reflections reflections;
    private final Set<Class<? extends CrudContainer>> containers;
    private final Map<Class<? extends Container>, Object> containersMap = new ConcurrentHashMap<>();

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
        try {
            Object instance = containersMap.get(CrudContainer.class);
            if (instance != null) {
                return (CrudContainer<AE>) instance;
            }
            Class<?> implClass = null;
            for (Class<?> aClass : containers) {
                if (!aClass.isAnnotationPresent(Deprecated.class)) {
                    implClass = aClass;
                }
            }
            if (implClass == null) {
                throw new RuntimeException("implementation is not present");
            }
            instance = implClass.getDeclaredConstructor().newInstance();
            containersMap.put(CrudContainer.class, instance);
            return (CrudContainer<AE>) instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("implementation is not present");
        }
    }
}
