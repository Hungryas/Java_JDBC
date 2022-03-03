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
    public List<User> getUsers(String request) {
        List<User> resultList = new ArrayList<>();
        List<String> userData = List.of(request.split(" "));
        // Определяем, на входе ФИО или нет
        if (userData.size() > 1) {
            for (User user : userRepository.getAll())
                if (user.getStatus() == UserStatus.ACTIVE && user.getFirstName().equals(userData.get(1)) && user.getLastName().equals(userData.get(0)) && user.getMiddleName().equals(userData.get(2))) {
                    resultList.add(user);
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
    public void deleteUsers(List<User> userList) {
        List<User> repository = userRepository.getAll();
        for (User user : userList)
            repository.get(repository.indexOf(user)).setStatus(UserStatus.DELETED);
        userRepository.saveAll(userList);
    }

    // обновить поля и вернуть обновленного пользователя, если статус ACTIVATED
    public User updateUser(String id, String firstName, String lastName, String middleName, String phone, String email) {
        User user = userRepository.getAll().get(userRepository.getListID().indexOf(id));
        if (user.getStatus() == UserStatus.ACTIVE) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setMiddleName(middleName);
            user.setPhone(phone);
            user.setEmail(email);
            return user;
        } else {
            System.out.printf("Can't update user with id %s, because it's status is DELETED", id);
            return null;
        }
    }

    // создает нового пользователя посредством вызова save у userRepository
    public User createUser(String firstName, String lastName, String middleName, String phone, String email) {
        return userRepository.save(new User(firstName, lastName, middleName, phone, email));
    }

}