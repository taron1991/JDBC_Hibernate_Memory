package Project;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public long askLong(String question) {
        return Long.parseLong(askStr(question));
    }
}
