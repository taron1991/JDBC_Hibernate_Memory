package Project;

public class ShowAllAction implements UserAction{
    @Override
    public String name() {
        return "==== SHOW ALL ====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        memTracker.findAll().forEach(System.out::println);
        return true;
    }
}
