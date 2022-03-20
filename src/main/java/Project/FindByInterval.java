package Project;

import java.util.List;

public class FindByInterval implements UserAction {
    @Override
    public String name() {
        return "====FINDBY INTERVAL====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String s = input.askStr("enter interval: ");
        List<Item> byIntervalDate = memTracker.findByIntervalDate(s);
            System.out.println(byIntervalDate);
            return true;
        }
}
