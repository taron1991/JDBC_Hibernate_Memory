package Project;

import java.util.Optional;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "====FINDBYID ACTION====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        long enter_id = input.askLong("enter id");
        Optional<Item> byId = memTracker.findById(enter_id);
        if (byId.isPresent()) {
            System.out.println(byId.get());
        } else {
            System.out.println("id not found try again");
        }
        return true;
    }
}
