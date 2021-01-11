package ua.com.alevel.core;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleContainer<E extends AbstractEntity> implements CrudContainer<E> {

    private final List<E> list = new ArrayList<>();

    public SimpleContainer() {
        System.out.println("SimpleContainer used now");
    }

    public List<E> findAll() {
        return list;
    }

    public E findById(int id) {
        if (CollectionUtils.isEmpty(list)) {
            throw new RuntimeException("not working this DB");
        }
        if (id < 1) {
            throw new RuntimeException("id can not be zero");
        }
        List<E> listById = list.stream().filter(e -> e.getId() == id).collect(Collectors.toList());
        return listById.get(0);
    }

    public void create(E e) {
        int size = list.size();
        ++size;
        e.setId(size);
        list.add(e);
    }

    public void update(E e) {
        E current = findById(e.getId());
        try {
            BeanUtils.copyProperties(e, current);
        } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    public void delete(int id) {
        list.removeIf(e -> e.getId() == id);
    }
}
