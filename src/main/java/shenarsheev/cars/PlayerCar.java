package shenarsheev.cars;

import shenarsheev.RaceConst;

public class PlayerCar extends AbstractCar{

    @Override
    public void setX(int x) {
        super.setX(x);
    }
    public void setY(int y){
        super.setY(y);
    }
    //line == (-1; 0; 1) метод для управления с клавиатуры
    public boolean setLine(int line){
        if(line == 1 && getX() == 4* RaceConst.carwidth)
            return false;
        else if (line == -1 && getX() == 0)
            return false;
        else
        {
            setX(getX() + line*RaceConst.carwidth);
            return true;
        }
    }
    public int getCalculatedY(){
        int timegap = getY()/RaceConst.velocity;
        return RaceConst.velocity*timegap;
    }
}
