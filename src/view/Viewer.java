package view;

public interface Viewer {
    void startGame();

    void getUpdate(Character[][] userView, long time);

    void showMessage(String message);

    void askUser(String message);

    void showErrorMessage(String message);

    void showWarningMessage(String message);

    void showGreetScreen();

    void showWinMessage();

    void showLoseMessage();

    void showLevelChoosing();

    void showTime(long time);
}
