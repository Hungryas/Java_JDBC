package Week05.api.impl;

import Week05.api.RoomAPIService;
import Week05.entity.Room;
import Week05.entity.RoomType;
import Week05.service.RoomService;
import Week05.service.exceptions.RequiredFieldMissedException;
import Week05.service.exceptions.RoomNotFoundException;

import java.util.List;
import java.util.Map;

public record RoomAPIServiceImpl(RoomService roomService) implements RoomAPIService {

    @Override
    public Room createRoom(Room room) {
        try {
            return roomService.createRoom(room);
        } catch (RequiredFieldMissedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Room updateRoom(String id, Room room) {
        try {
            return roomService.updateRoom(id, room);
        } catch (RoomNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Room getRoom(String id) {
        try {
            return roomService.getBy(id);
        } catch (RoomNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<RoomType, List<Room>> getRoomsGroupedByType() {
        return roomService.getRoomsGroupByType();
    }

    @Override
    public void deleteRoom(Room room) {
        roomService.deleteRoom(room);
    }
}
