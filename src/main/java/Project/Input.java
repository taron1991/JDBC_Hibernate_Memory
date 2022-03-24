package Project;

/**
 * Интерфейс для коммуникации с пользователем
 */
public interface Input {
    /**
     * Метод для коммуникации с пользователем
     * @param question
     * @return строку
     */
    public String askStr(String question);

    /**
     * Метод для коммуникации с пользователем
     * @param question
     * @return тип long
     */
    public long askLong(String question);
}
