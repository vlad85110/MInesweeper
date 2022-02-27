package view.console;

import view.Updater;

public class Printer implements Updater {
    @Override
    public void getUpdate(Character[][] userView) {
        printMap(userView, userView.length);
    }

    public static void printMap(Character[][] field, int size) {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                System.out.print(field[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printTime() {

    }
}
