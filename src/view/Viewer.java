package view;

public interface Viewer {
    public void getUpdate(Character [][] userView);

    public void showMessage(String message);

    public void showErrorMessage(String message);

    public void showTime();

    public void showWarningMessage(String message);

    public void showGreetScreen();

    public void showWinMessage();

    public void showLoseMessage();

    public void showLevelChoosing();
}
