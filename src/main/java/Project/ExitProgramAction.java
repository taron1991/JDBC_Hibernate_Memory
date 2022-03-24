package Project;

public class ExitProgramAction implements UserAction {
    @Override
    public String name() {
        return "====EXIT PROGRAMM===";
    }

    @Override
    public boolean execute(Input input, Store store) {
        return false;
    }
}
