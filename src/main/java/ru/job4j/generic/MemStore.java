package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    /**
     * add() - метод добавляет в хранилище объект типа T, при этом метод ничего не возвращает;
     * @param model - объект
     */
    @Override
    public void add(T model) {
        if (!storage.containsKey(model.getId())) {
            storage.put(model.getId(), model);
        }
    }

    /**
     * replace() - метод выполняет замену объекта по id, на объект который передается в параметрах метода
     * @param id - id
     * @param model - - объект
     * @return и возвращает true, если замена выполнена успешно;
     */
    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, storage.get(id), model);
    }

    /**
     * delete() - метод удаляет пару ключ-значение по id, который передается в метод
     * @param id - id
     * @return - возвращает true, если удаление выполнено успешно;
     */
    @Override
    public boolean delete(String id) {
        return storage.remove(id, storage.get(id));
    }

    /**
     * findById() - метод возвращает объект по ключу, который равен id, передаваемый в качестве параметра метода
     * @param id - id
     * @return - возвращает null если по такому ключу в Map еще нет пару ключ-значение.
     */
    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}