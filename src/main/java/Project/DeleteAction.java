package Project;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "====DELETE ACTION====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        long id = input.askLong("enter id");
        boolean delete = store.delete(id);
        if(delete){
            System.out.println("successfuly deleted");
        }
        else{
            System.out.println("unsuccessfully deleted try again");
        }
        return true;
    }
}
