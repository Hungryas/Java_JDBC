package Week04.services.impl;

import Week04.entities.User;
import Week04.entities.UserStatus;
import Week04.repository.UserRepository;
import Week04.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // поиск по пользователям
    @Override
    public List<User> getUsers(String request) {
        List<User> resultList = new ArrayList<>();
        // Определяем, на входе ФИО или нет
        if (request.split(" ", 2).length == 3) {
            for (User user : userRepository.getAll()) {
                var userName = String.join(" ", user.getLastName(), user.getFirstName(), user.getMiddleName());
                if (user.getStatus() == UserStatus.ACTIVE && userName.equals(request)) {
                    resultList.add(user);
                }
            }
        } else {
            for (User user : userRepository.getAll())
                if (user.getStatus() == UserStatus.ACTIVE && (user.getPhone().equals(request)) || user.getEmail().equals(request)) {
                    resultList.add(user);
                }
        }
        return resultList;
    }

    // выставляет найденным пользователям статус DELETED
    @Override
    public void deleteUsers(List<User> userList) {
        List<User> repository = userRepository.getAll();
        for (User user : userList)
            repository.get(repository.indexOf(user)).setStatus(UserStatus.DELETED);
        userRepository.saveAll(userList);
    }

    // обновить поля и вернуть обновленного пользователя, если статус ACTIVE
    @Override
    public User updateUser(String id, String firstName, String lastName, String middleName, String phone, String email) {
        for (User user : userRepository.getAll()) {
            if (user.getId().equals(id) && user.getStatus() == UserStatus.ACTIVE) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setMiddleName(middleName);
                user.setPhone(phone);
                user.setEmail(email);
                return user;
            }
        }
        System.out.printf("Can't update user with id %s, because it's status is DELETED\n", id);
        return null;
    }

    // создает нового пользователя посредством вызова save у userRepository
    @Override
    public User createUser(String firstName, String lastName, String middleName, String phone, String email) {
        return userRepository.save(new User(firstName, lastName, middleName, phone, email));
    }

}