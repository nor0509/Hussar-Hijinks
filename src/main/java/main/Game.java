package main;

public class Game {

    private GamePanel gamePanel;
    private GameWindow gameWindow;

    public Game() {

        this.gamePanel = new GamePanel();
        this.gameWindow = new GameWindow(gamePanel);

    }
}
