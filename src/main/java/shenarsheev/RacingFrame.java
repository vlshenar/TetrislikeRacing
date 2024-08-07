package shenarsheev;

import shenarsheev.RaceView.RaceDisplay;
import shenarsheev.cars.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RacingFrame extends JFrame {
    private RaceDisplay raceDisplay = null;
    private RacingManager racingManager = null;
    private Timer t = null;
    public RacingFrame(){
        raceDisplay = new RaceDisplay();
        racingManager = new RacingManager(raceDisplay);
        ActionListener listener = new Timemover();
        t = new Timer(20, listener);
        addKeyListener(new PlayerListiner());
        add(raceDisplay, BorderLayout.CENTER);
        setSize(450, 470);
        setIconImage(raceDisplay.getCarImg());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        raceDisplay.setStartPicture(true);

    }
    //запускатель процесса игры
    private class Timemover implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean tmp = racingManager.raceProcessing();
            //остановить игру, если авария
            if(!tmp){
                raceDisplay.setStartPicture(!tmp);
                t.stop();
            }
        }
    }
    //обработка команд пользователя
    private class PlayerListiner extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            //вправо
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                racingManager.getPlayer().setLine(1);
            //влево
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
                racingManager.getPlayer().setLine(-1);
            //ускорение, если клавиша нажата
            if(e.getKeyCode() == KeyEvent.VK_UP) Car.setBoost(true);
            //начать игру
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                raceDisplay.setStartPicture(false);
                t.start();
            }
        }
        //вернуть начальную скорость, если клавиша отпущена
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP) Car.setBoost(false);
        }
    }
}
