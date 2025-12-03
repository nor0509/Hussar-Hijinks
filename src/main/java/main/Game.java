package main;

public class Game implements Runnable{

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    private final int FPS_SET = 144;

    public Game() {

        this.gamePanel = new GamePanel();
        this.gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    @Override
    public void run() {
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        double timePerFrame = 1000000000.0 / FPS_SET;
        long now;

        long lastFrame = System.nanoTime();


        while(true){

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }

            now = System.nanoTime();
            if(now - lastFrame>= timePerFrame){

                gamePanel.repaint();
                frames++;
                lastFrame = now;
            }

        }
    }
}
