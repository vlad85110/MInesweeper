package view;

public interface Viewer {
    void startGame();

    void getUpdate(Character[][] userView, long time);

    void showMessage(String message);

    void askUser(String message);

    void showGreetScreen();

    void showLevelChoosing();

    void showTime(long time);

    String waitAction() throws InterruptedException;
}
