package view;

public interface Viewer {
    void startGame();

    void getUpdate(Character[][] userView, long time);

    void showMessage(String message);

    void showWarning(String message);

    void askUser(String message);

    void showGreetScreen();

    void showLevelChoosing();

    String waitAction() throws InterruptedException;

    void setAlreadyCreated(boolean alreadyCreated);
}
