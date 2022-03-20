package Project;

public class DeleteAction implements UserAction{
    @Override
    public String name() {
        return " ==== DELETE ITEM ==== ";
    }

    @Override
    public boolean execute(Input input, Store store) {
       long id = input.askLong("Enter id: ");
       boolean status = store.delete(id);
       if(status){
           System.out.println("successfully");
       }else{
           System.out.println("unseccessfully");
       }
        return true;
    }
}
