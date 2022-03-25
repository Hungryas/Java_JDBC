package Week05.service.impl;

import Week05.entity.Room;
import Week05.entity.RoomType;
import Week05.repository.RoomRepository;
import Week05.service.RoomService;
import Week05.service.exceptions.RequiredFieldMissedException;
import Week05.service.exceptions.RoomNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record RoomServiceImpl(RoomRepository roomRepository) implements RoomService {

    // нужно с помощью roomRepository вернуть комнату по идентификатору, в случае
    // если комната не найдена -- выбросить RoomNotFoundException
    @Override
    public Room getBy(String id) throws RoomNotFoundException {
        for (Room room : roomRepository.getAll()) {
            if (room.getId().equals(id)) return room;
        }
        throw new RoomNotFoundException();
    }

    // здесь нужно с помощью roomRepository создать комнату и присвоить ей id
    // проверить, что заполнены поля roomNumber, floor, type, price --
    // иначе выкинуть исключение RequiredFieldMissedException
    // поле bookings не заполнять
    @Override
    public Room createRoom(Room room)
            throws RequiredFieldMissedException {
        if (room.getRoomNumber() == null) throw new RequiredFieldMissedException("Field \"Number\" is missed");
        if (room.getFloor() == null) throw new RequiredFieldMissedException("Field \"Floor\" is missed");
        if (room.getType() == null) throw new RequiredFieldMissedException("Field \"Type\" is missed");
        if (room.getPrice() == null) throw new RequiredFieldMissedException("Field \"Price\" is missed");
        roomRepository.save(room);
        return room;
    }

    // здесь нужно проверить, что комната с таким id существует
    // обновить данные комнаты с помощью roomRepository
    @Override
    public Room updateRoom(String id, Room room)
            throws RoomNotFoundException {
        if (roomRepository.getBy(id) == null) throw new RoomNotFoundException();
        for (Room existRoom : roomRepository.getAll()) {
            if (existRoom.getId().equals(id)) {
                existRoom.setRoomNumber(room.getRoomNumber());
                existRoom.setFloor(room.getFloor());
                existRoom.setType(room.getType());
                existRoom.setDescription(room.getDescription());
                existRoom.setPrice(room.getPrice());
                existRoom.setBookings(room.getBookings());
            }
        }
        System.out.printf("Room %s is updated.\n", room.getRoomNumber());
        return room;
    }

    // удалить переданную комнату с помощью roomRepository вместе со всеми прикрепленными к ней
    // бронированиями
    @Override
    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }

    // получить комнаты, сгруппированные по типу, то есть должна получиться map вида
    // {LUX: [Room1, Room2, Room3], ECONOMY: [Room5, Room6], ... }
    @Override
    public Map<RoomType, List<Room>> getRoomsGroupByType() {
        Map<RoomType, List<Room>> roomsGroupByType = new HashMap<>();
        for (Room room : roomRepository.getAll()) {
            RoomType currentRoomType = room.getType();
            if (roomsGroupByType.get(currentRoomType) == null) {
                roomsGroupByType.put(currentRoomType, new ArrayList<>(List.of(room)));
            } else {
                roomsGroupByType.get(currentRoomType).add(room);
            }
        }
        return roomsGroupByType;
    }
}
