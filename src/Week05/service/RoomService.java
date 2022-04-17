package Week05.service;

import Week05.entity.Room;
import Week05.entity.RoomType;
import Week05.service.exceptions.RequiredFieldMissedException;
import Week05.service.exceptions.RoomNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Сервис комнат.
 */
public interface RoomService {
    /**
     * Получить комнату по идентификатору.
     *
     * @param id идентификатор комнаты
     * @throws RoomNotFoundException если комната с таким id не найдена
     * @return комната
     */
    Room getBy(String id);

    /**
     * Создать комнату.
     *
     * @param room комната
     * @return созданная комната с присвоенным идентификатором
     */
    Room createRoom(Room room) throws RequiredFieldMissedException;

    /**
     * Обновить данные комнаты.
     *
     * @param id идентификатор обновляемой комнаты
     * @param room обновленная комната
     * @throws RoomNotFoundException если комната с таким id не найдена
     * @return обновленная комната
     */
    Room updateRoom(String id, Room room);

    /**
     * Удалить комнату.
     *
     * @param room комната
     */
    void deleteRoom(Room room);

    /**
     * Получить комнаты, сгруппированные по типу.
     */
    Map<RoomType, List<Room>> getRoomsGroupByType();
}
