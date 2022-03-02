package view.console;

import view.Viewer;

public class ConsoleViewer implements Viewer {
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showErrorMessage(String message) {
        System.err.println("Error : " + message);
    }

    @Override
    public void showWarningMessage(String message) {
        System.out.println("Warning : " + message);
    }

    @Override
    public void showGreetScreen() {
        System.out.println("Minesweeper");
        System.out.println("1.New Game");
        System.out.println("2.High scores");
        System.out.println("3.About");
        System.out.println("4.Exit");
    }
    
    @Override
    public void getUpdate(Character[][] userView) {
        printMap(userView, userView.length);
    }
    
    public static void printMap(Object[][] field, int size) {
        System.out.print(" " + "|");
        for (int i = 0; i < size; i++) {
            System.out.print(i % 10 + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print("_ ");
        }
        System.out.println("_");

        for (int i = 0; i < size; i++) {
            System.out.print(i % 10 + "|");
            for (int j = 0; j < size; j++) {
                System.out.print(field[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void showWinMessage() {
        System.out.println("You win!");
    }

    @Override
    public void showLoseMessage() {
        System.out.println("You lose");
    }

    @Override
    public void showLevelChoosing() {
        System.out.println("Choose difficulty:");
        System.out.println("1.Beginner");
        System.out.println("2.Intermediate");
        System.out.println("3.Expert");
    }

    @Override
    public void showTime() {

    }
}
