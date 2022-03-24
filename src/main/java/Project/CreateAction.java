package Project;

public class CreateAction implements UserAction {
    @Override
    public String name() {
        return "====CREATE ACTION====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        Item item = new Item();
        String name = input.askStr("enter name");
        String description = input.askStr("enter description");
        item.setName(name);
        item.setDescription(description);
        store.add(item);
        return true;
    }
}
