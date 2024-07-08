package shenarsheev.RaceView;

import shenarsheev.RaceConst;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import static shenarsheev.RaceConst.*;

public class RaceDisplay extends JComponent {
    private int capacityRoad = 4;
    private int playerX;
    private int playerY = 0;
    private int[] carX = null;
    private int[] carY = null;
    //смещение относительно экрана
    private int biasX = 25;
    private int biasY = -200;
    private Image img;
    private Image startImg;
    private Image roadImg = null;
    private Image carImg = null;
    public RaceDisplay(){
        carX = new int[capacityRoad];
        carY = new int[capacityRoad];
        URL urlImg = getClass().getResource("/racebegin.gif");
        startImg = new ImageIcon(urlImg).getImage();
        urlImg = getClass().getResource("/road.gif");
        roadImg = new ImageIcon(urlImg).getImage();
        urlImg = getClass().getResource("/car.gif");
        carImg = new ImageIcon(urlImg).getImage();
    }
    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }
    public boolean setCarXY(int numbercar, int x, int y){
        if (numbercar > capacityRoad)
            return false;
        carX[numbercar] = x;
        carY[numbercar] = y;
        return true;
    }
    public void setStartPicture(boolean show_start){
        img = (show_start == true)? startImg: roadImg;
        for (int i = 0; i < capacityRoad; i++){
            carX[i] = 0; carY[i] = 0;
        }
        this.repaint();
    }
    public Image getCarImg(){
        return carImg;
    }
    public void paintComponent(Graphics g){
        g.drawImage(img, 0, 0, roadwidth, roadheight, null);
        g.drawImage(carImg, playerX+biasX, playerY+biasY, carwidth, carheight, null);
        g.drawImage(carImg, carX[0]+biasX, carY[0]+biasY, carwidth, carheight, null);
        g.drawImage(carImg, carX[1]+biasX, carY[1]+biasY, carwidth, carheight, null);
        g.drawImage(carImg, carX[2]+biasX, carY[2]+biasY, carwidth, carheight, null);
        g.drawImage(carImg, carX[3]+biasX, carY[3]+biasY, carwidth, carheight, null);
        g.drawImage(roadImg, 0, roadheight, roadwidth, 2*carheight, null);
    }
}
