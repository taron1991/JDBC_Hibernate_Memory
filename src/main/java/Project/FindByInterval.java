package Project;

import java.time.LocalDateTime;
import java.util.List;

public class FindByInterval implements UserAction {
    @Override
    public String name() {
        return "====FINDBY INTERVAL====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String interval = input.askStr("enter interval: ");
        Pair<LocalDateTime, LocalDateTime> TimePair =TrackerUtil.transformationStringToLocalDateTime(interval);
        List<Item> byIntervalDate = memTracker.findByIntervalDate(TimePair.getFirstParametr(), TimePair.getSecondParametr());

        System.out.println(byIntervalDate);
        return true;
    }
}
