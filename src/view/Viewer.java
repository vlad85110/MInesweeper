package view;

import model.data.UpdateData;

public interface Viewer {
    void startGame();

    void endGame();

    void getUpdate(UpdateData data, long time);

    void showList(String message);

    void showText(String message);

    void showWarning(String message);

    void askUser(String message);

    void showGreetScreen();

    void showLevelChoosing();

    String waitAction() throws InterruptedException;

    void setAlreadyCreated(boolean alreadyCreated);
}
