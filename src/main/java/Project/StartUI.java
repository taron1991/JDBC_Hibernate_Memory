package Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.util.stream.Collectors;

public class StartUI {

    private static Connection connection;
    private static final Logger log = LoggerFactory.getLogger(StartUI.class);

    public void init(Input input, Store memTracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
          long select = input.askLong("Select: ");
            if (select < 0 || select > actions.length) {
                System.out.println("no such a menu");
                continue;
            }

            UserAction action = actions[(int) select-1];
            run = action.execute(input, memTracker);
        }
    }

    private void showMenu(UserAction[] actions) {

        // выводить всё меню (цикл + system.out.println)
        System.out.println("menu: ");
        for (int i = 0; i < actions.length; i++) {
            System.out.println(i+1 + ". " + actions[i].name());
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();

        try {
            Store store = new JdbcStore(connection);
            store.init();
            UserAction[] actions = {
                    new CreateAction(),
                    new DeleteAction(),
                    new ReplaceAction(),
                    new ShowAllAction(),
                    new ExitProgramAction(),
                    new FindAllAction(),
                    new FindByIdAction(),
                    new FindByNameAction(),
                    new FindByInterval()
            };
            new StartUI().init(input,store, actions);

        } catch (Exception e) {
            log.error("error while running");
        }
    }
}
