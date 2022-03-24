package Project;

import java.util.Optional;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "====FINDBY ID ACTION====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        long id = input.askLong("enter id");
        Optional<Item> byId = store.findById(id);
        if (byId.isPresent()) {
            System.out.println(byId.get());
        } else {
            System.out.println("id not found try again");
        }
        return true;
    }
}
