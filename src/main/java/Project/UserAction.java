package Project;

/**
 *Интерфейс для обработки данных от пользователя
 */
interface UserAction {
    /**
     *
     * @return название операции
     */
    String name();

    /**
     *метод для обработки данных от пользователя
     * @param input
     * @param store
     * @return булево значение
     */
    boolean execute(Input input, Store store);
}
