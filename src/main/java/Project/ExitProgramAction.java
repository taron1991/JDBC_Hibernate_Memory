package Project;

public class ExitProgramAction implements UserAction{
    @Override
    public String name() {
        return " ==== EXIT PROGRAM ==== ";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        return false;
    }
}
