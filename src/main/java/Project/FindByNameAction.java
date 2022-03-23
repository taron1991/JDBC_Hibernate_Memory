package Project;

import java.util.List;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "====FINDBYNAME ACTION====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String ente_name = input.askStr("enter name");
        List<Item> byName = memTracker.findByName(ente_name);
        if (byName.isEmpty()) {
            System.out.println("name not fund try again");
        } else {
            System.out.println(byName);
        }
        return true;
    }
}
