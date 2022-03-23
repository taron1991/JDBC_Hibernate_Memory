package Project;

public class CreateAction implements UserAction {
    @Override
    public String name() {
        return "====CREATE ACTION====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        Item item = new Item();
        String enter_name = input.askStr("enter name");
        String enter_description = input.askStr("enter description");
        item.setName(enter_name);
        item.setDescription(enter_description);
        memTracker.add(item);
        return true;
    }
}
