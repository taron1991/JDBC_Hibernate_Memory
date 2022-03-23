package Project;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "====REPLACE ACTION====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        Item item = new Item();
        long id = input.askLong("enter id: ");
        String name = input.askStr("enter name: ");
        String desc = input.askStr("enter description: ");
        item.setDescription(desc);
        item.setName(name);
        boolean update = memTracker.update(id, item);
        if (update) {
            System.out.println("successfully");
        } else {
            System.out.println("unsuccessfully");
        }
        return true;
    }
}
