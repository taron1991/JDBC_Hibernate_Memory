package Project;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    public String askStr(String question) { //enter_id
        System.out.print(question); //enter_id
        return scanner.nextLine(); // "12123"
    }

    public long askLong(String question) {   // "enter_id"
        return Long.parseLong(askStr(question)); //12123
    }
}
