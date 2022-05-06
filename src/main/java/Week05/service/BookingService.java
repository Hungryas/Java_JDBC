package Week05.service;

import Week05.entity.Booking;
import Week05.service.exceptions.BookingNotFoundException;
import Week05.service.exceptions.RequiredFieldMissedException;
import Week05.service.exceptions.RoomNotFoundException;

/**
 * Сервис бронирований.
 */
public interface BookingService {
    /**
     * Получить бронирование по идентификатору.
     *
     * @param id идентификатор
     * @throws BookingNotFoundException если бронирование с таким id не найдено
     * @return бронирование
     */
    Booking getBy(String id);

    /**
     * Создать бронирование.
     *
     * @param booking бронирование
     * @return созданное бронирование с присвоенным идентификатором
     */
    Booking createBooking(Booking booking) throws RequiredFieldMissedException;

    /**
     * Обновить бронирование.
     *
     * @param booking обновленное бронирование
     * @throws BookingNotFoundException если бронирование с таким id не найдено
     * @return обновленное бронирование
     */
    Booking updateBooking(String id, Booking booking) throws RequiredFieldMissedException;

    /**
     * Удалить бронирование.
     *
     * @param booking бронирование
     */
    void deleteBooking(Booking booking);
}
