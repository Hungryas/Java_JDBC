package Week04.services;

import Week04.entities.User;

import java.util.List;

public interface UserService {
    List<User> getUsers(String request) ;
    void deleteUsers(List<User> user);
    User updateUser(String id, String firstName, String lastName, String middleName, String phone, String email);
    User createUser(String firstName, String lastName, String middleName, String phone, String email);
}
