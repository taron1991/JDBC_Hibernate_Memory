package Project;

import java.util.List;

public class FindAllAction implements UserAction {
    @Override
    public String name() {
        return "====FINDALL ACTION====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        List<Item> all = memTracker.findAll();
        if(all.isEmpty()){
            System.out.println("our list is empty");
        }
        System.out.println(all);
        return true;
    }
}
