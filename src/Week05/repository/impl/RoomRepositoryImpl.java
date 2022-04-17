package Week05.repository.impl;

import Week05.entity.Room;
import Week05.repository.RoomRepository;

import java.util.HashSet;
import java.util.Set;

public class RoomRepositoryImpl implements RoomRepository {
    private static final Set<Room> rooms = new HashSet<>();

    public RoomRepositoryImpl() {
    }

    // метод создает новую комнату (добавляет в поле rooms), если такого id нет и
    // обновляет, если комната с таким идентификатором существует
    @Override
    public Room save(Room room) {
        rooms.remove(room);
        rooms.add(room);
        System.out.printf("Room %s is saved in repository.\n", room.getRoomNumber());
        return room;
    }

    // удаляет комнату из поля rooms, если такой комнаты нет -- ничего не происходит
    @Override
    public void delete(Room room) {
        if (rooms.contains(room)) {
            rooms.remove(room);
            System.out.printf("Room %s is deleted from repository.\n", room.getRoomNumber());
        }
    }

    // возвращает комнату по идентификатору, если такой комнаты нет -- вернуть null
    @Override
    public Room getBy(String id) {
        for (Room room : rooms)
            if (room.getId().equals(id)) return room;
        return null;
    }

    // возвращает все комнаты
    @Override
    public Set<Room> getAll() {
        return rooms;
    }
}
