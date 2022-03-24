package Project;

import java.util.List;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "====FINDBYNAME ACTION====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        String name = input.askStr("enter name");
        List<Item> byName = store.findByName(name);
        if (byName.isEmpty()) {
            System.out.println("name not found try again");
        } else {
            System.out.println(byName);
        }
        return true;
    }
}
