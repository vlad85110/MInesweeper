package view.console;

public class Printer {
    public static void print(Character[][] field, int size) {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
}
