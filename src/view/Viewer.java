package view;

public interface Viewer {
    public void getUpdate(Character [][] userView);

    public void showErrorMessage(String message);

    public void showWarningMessage(String message);

    public void showGreetScreen();

    public void showWinMessage();

    public void showLoseMessage();
}
