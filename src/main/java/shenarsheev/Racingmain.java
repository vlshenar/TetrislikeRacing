package shenarsheev;


import java.awt.*;

/**
 * Let's drive!
 *
 */
public class Racingmain
{
    public static void main( String[] args )
    {
       EventQueue.invokeLater(new Runnable(){
           public void run(){
                RacingFrame rasing = new RacingFrame();
                rasing.setTitle("Simple Racing");
                rasing.setVisible(true);
           }
       });

    }
}
