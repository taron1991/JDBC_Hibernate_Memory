package Project;

import java.util.List;

public class FindAllAction implements UserAction {
    @Override
    public String name() {
        return "====FIND ALL ACTION====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        List<Item> all = store.findAll();
        if(all.isEmpty()){
            System.out.println("our list is empty");
        }
        System.out.println(all);
        return true;
    }
}
