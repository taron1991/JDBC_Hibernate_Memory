package Project;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * предоставляет CRUD и допметоды для работы с сущностью
 * @author Taron
 * @since 1.0
 */
public interface Store {

    /**
     * инициализатор
     */
    void init();


    /**
     * добавляет сущность в хранилище
     *
     * @param item-сущность
     * @return сущность со сгенерированным id
     */
    Item add(Item item);

    /**
     * удаляем обьект
     *
     * @param id наш айди itema
     * @return true or false в зависимости есть ли обьект
     */
    boolean delete(long id);

    /**
     * редактируем обьект
     *
     * @param id
     * @param item обьект наш
     * @return true or false  в зависимости есть ли обьект
     */
    boolean update(long id, Item item);

    /**
     * поиск всех людей в бд
     *
     * @return список людей
     */
    List<Item> findAll();

    /**
     * поиск человека по айди
     *
     * @param id
     * @return Optional
     */
    Optional<Item> findById(long id);

    /**
     * поиск человека по имени
     *
     * @param name имя человека
     * @return список людей
     */
    List<Item> findByName(String name);

    /**
     * поиск людей по дате добавления(интервал)
     *
     * @param start начало интервала
     * @param end   конец интервала
     * @return список людей
     */
    List<Item> findByIntervalDate(LocalDateTime start, LocalDateTime end);
}