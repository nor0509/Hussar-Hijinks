package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage subImg;



    public GamePanel() {
        MouseInputs mouseInput = new MouseInputs(this);

        importImg();

        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/Run-Sheet.png");
        try{
            img = ImageIO.read(is);
        } catch(IOException e){
            e.printStackTrace();
        }

    }


    private void setPanelSize() {
        Dimension sizeMin = new Dimension(1280,800);
        Dimension sizePr = new Dimension(1280,800);
        Dimension sizeMax = new Dimension(1280,800);
        setMinimumSize(sizeMin);
        setPreferredSize(sizePr);
        setMaximumSize(sizeMax);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImg = img.getSubimage(4*80,0, 80, 80);
        g.drawImage(subImg, 0 ,0, null);

        Toolkit.getDefaultToolkit().sync();
    }
}

