package Project;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return " ==== REPLACE ITEM ==== ";
    }

    @Override
    public boolean execute(Input input, Store store) {
        Item item = new Item();
        long id = input.askLong("enter id:");
        String name = input.askStr("enter name");
        String description = input.askStr("enter description");
        item.setName(name);
        item.setDescription(description);

        boolean status = store.update(id, item);
        if (status) {
            System.out.println("successfully");
        } else {
            System.out.println("unseccessfully");
        }
        return true;
    }
}
