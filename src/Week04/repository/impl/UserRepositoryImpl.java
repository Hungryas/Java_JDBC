package Week04.repository.impl;

import Week04.entities.User;
import Week04.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private final List<String> listID = new ArrayList<>();

    // возвращает список всех пользователей
    public List<User> getAll() {
        return users;
    }

    // возвращает пользователя по идентификатору или null
    public User getBy(String id) {
        if (getListID().contains(id)) return users.get(listID.indexOf(id));
        else return null;
    }

    // сохраняет пользователя и возвращает его
    public User save(User user) {
        if (getListID().contains(user.getId())) users.set(listID.indexOf(user.getId()), user);
        else users.add(user);
        return users.get(users.indexOf(user));
    }

    // сохраняет спискок пользователей и возвращает его
    public List<User> saveAll(List<User> userList) {
        List<User> savedUsers = new ArrayList<>();
        for (User user : userList) savedUsers.add(save(user));
        return savedUsers;
    }

    // содержит только id для облегчения поиска по листу
    public List<String> getListID() {
        listID.clear();
        for (User user : users) {
            listID.add(user.getId());
        }
        return listID;
    }
}
