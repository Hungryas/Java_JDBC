package Week04.repository.impl;

import Week04.entities.User;
import Week04.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();

    // возвращает список всех пользователей
    @Override
    public List<User> getAll() {
        return users;
    }

    // возвращает пользователя по идентификатору или null
    @Override
    public User getBy(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return users.get(users.indexOf(user));
            }
        }
        return null;
    }

    // сохраняет пользователя и возвращает его
    @Override
    public User save(User user) {
        if (users.contains(user)) users.set(users.indexOf(user), user);
        else users.add(user);
        return users.get(users.indexOf(user));
    }

    // сохраняет список пользователей и возвращает его
    @Override
    public List<User> saveAll(List<User> userList) {
        List<User> savedUsers = new ArrayList<>();
        for (User user : userList) savedUsers.add(save(user));
        return savedUsers;
    }
}