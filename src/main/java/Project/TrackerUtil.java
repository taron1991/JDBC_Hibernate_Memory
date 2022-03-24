package Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для обработки дополнительных значений методов
 */
public final class TrackerUtil {
    /**
     * Логирование(библиотека slf4j)
     */
    private static final Logger LOG = LoggerFactory.getLogger(TrackerUtil.class);


    /**
     * Метод для парсинга класс LocalDateTime в TimeStamp
     * @param localDateTime
     * @return TimeStamp
     */
    public static Timestamp parserTime(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Метод для генерации Id используя класс UUID
     * @return ай ди типа long
     */
    public static Long generateId() {
        String key = UUID.randomUUID().toString();
        String result = "";
        Pattern compile = Pattern.compile("\\d+");
        Matcher matcher = compile.matcher(key);


        while (matcher.find()) {
            result += matcher.group();
        }

        BigInteger bigInteger = new BigInteger(result);

        return bigInteger.longValue();
    }


    /**
     * Метод для форматирования(парсинга) времени в разбитую на две части пару LocalDateTime
     * @param text время в формате String
     * @return параметризованный класс с двумя значениями LocalDateTime
     */
    public static Pair<LocalDateTime, LocalDateTime> transformationStringToLocalDateTime(String text) {

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();

        //1 variant    //2020-02-12 23:15 - 2020-03-12 23:55
        //2 variant    //16:23-16:35
        try {
            String[] splitText = text.split(Constants.HYPHEN);
            String[] splitDateStart = splitText[0].split(Constants.SPACE);
            String[] splitDateEnd = splitText[1].split(Constants.SPACE);
            if (text.length() > 15) {
                String[] split = text.split("\\s{1}-\\s{1}");
                String s = split[0];
                String s1 = split[1];
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                start = LocalDateTime.parse(s, dateTimeFormatter);
                end = LocalDateTime.parse(s1, dateTimeFormatter);
                return new Pair<>(start, end);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); //16:23-16:35
                LocalTime startTime = LocalTime.parse(splitText[0], formatter);//16:23
                LocalTime endTime = LocalTime.parse(splitText[1], formatter);//16:35

                return new Pair<>(
                        start
                                .withHour(startTime.getHour())
                                .withMinute(startTime.getMinute()),
                        end
                                .withHour(endTime.getHour())
                                .withMinute(endTime.getMinute()));
            }

        } catch (Exception e) {
            LOG.info(
                    "Используйте следующий формат данных, если хотите найти пользователей за конкретную дату: {}.\n"
                            + "Если хотите найти пользователей за текущую дату, то введите следующий формат даты: {}.",
                    Constants.FORMAT_DATE_HH_MM,
                    Constants.FORMAT_HH_MM);
        }
        return null;
    }
}