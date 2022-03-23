package Project;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "====DELETE ACTION====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        long enter_id = input.askLong("enter id");
        boolean delete = memTracker.delete(enter_id);
        if(delete){
            System.out.println("successfuly deleted");
        }
        else{
            System.out.println("unsuccessfully deleted try again");
        }
        return true;
    }
}
