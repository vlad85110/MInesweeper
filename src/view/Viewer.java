package view;

public interface Viewer {
    public void startGame();

    public void getUpdate(Character [][] userView, long time);

    public void showMessage(String message);

    public void showErrorMessage(String message);

    public void showWarningMessage(String message);

    public void showGreetScreen();

    public void showWinMessage();

    public void showLoseMessage();

    public void showLevelChoosing();

    public void showTime(long time);
}
