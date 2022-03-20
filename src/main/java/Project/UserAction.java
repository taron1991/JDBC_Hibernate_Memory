package Project;

public interface UserAction {
    String name(); /*- чисто выводить какой пункт меню выбрал пользак*/
    boolean execute(Input input, Store memTracker);
}
