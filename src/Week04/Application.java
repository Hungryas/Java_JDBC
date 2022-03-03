package Week04;

import Week04.entities.User;
import Week04.repository.UserRepository;
import Week04.repository.impl.UserRepositoryImpl;
import Week04.services.UserService;
import Week04.services.impl.UserServiceImpl;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);

        // сохраняет спискок пользователей и возвращает его
        userRepository.saveAll(Arrays.asList(new User("Иван",
                "Иванов",
                "Иванович",
                "+7(999)999-99-91",
                "ivanov@mail.com"), new User("Петр",
                "Петров",
                "Петрович",
                "+7(999)999-99-91",
                "petrov@mail.com")));
        // создает нового пользователя посредством вызова save у userRepository
        userService.createUser("Сидор",
                "Сидоров",
                "Сидорович",
                "+7(999)999-99-92",
                "sidorov@mail.com");
        // возвращает список всех пользователей
        System.out.println(userRepository.getAll().toString());
        // возвращает пользователя по идентификатору или null
        System.out.println(userRepository.getBy(userRepository.getListID().get(0)));
        // поиск по пользователям
        System.out.println(userService.getUsers("+7(999)999-99-91"));
        // выставляет найденным пользователям статус DELETED
        userService.deleteUsers(userRepository.getAll().subList(1, 3));
        // обновить поля и вернуть обновленного пользователя, если статус ACTIVATED
        userService.updateUser(userRepository.getListID().get(0), "Ivan",
                "Ivanov",
                "Ivanovich",
                "+7(999)999-99-90",
                "ivanov@mail.com");
        userService.updateUser(userRepository.getListID().get(1), "Petr",
                "Petrov",
                "Petrovich",
                "+7(999)999-99-91",
                "petrov@mail.com");
        // финальный лист
        System.out.println(userRepository.getAll().toString());
    }
}