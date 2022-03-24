package Project;

import java.time.LocalDateTime;
import java.util.List;

public class FindByInterval implements UserAction {
    @Override
    public String name() {
        return "====FIND BY INTERVAL====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        String interval = input.askStr("enter interval: ");
        Pair<LocalDateTime, LocalDateTime> TimePair =TrackerUtil.transformationStringToLocalDateTime(interval);
        List<Item> byIntervalDate = store.findByIntervalDate(TimePair.getFirstParametr(), TimePair.getSecondParametr());

        System.out.println(byIntervalDate);
        return true;
    }
}
