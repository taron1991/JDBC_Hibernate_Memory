package Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TrackerUtil {
    private static final Logger LOG = LoggerFactory.getLogger(TrackerUtil.class);


    public static Timestamp parserTime(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

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
                LocalTime startTime = LocalTime.parse(splitText[0], formatter);
                LocalTime endTime = LocalTime.parse(splitText[1], formatter);

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
