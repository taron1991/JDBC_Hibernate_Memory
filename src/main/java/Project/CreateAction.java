package Project;

public class CreateAction implements UserAction{
    public String name() {
        return "==== CREATE A NEW ITEM ====";
    }

    public boolean execute(Input input, Store store) {
        System.out.println("==== Create Item ====");
        String name = input.askStr("Enter name: ");
        String description = input.askStr("Enter description: ");
        Item item = new Item(name, description);
        store.add(item);
        return true;
    }
}
