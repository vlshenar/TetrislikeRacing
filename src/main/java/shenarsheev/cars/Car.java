package shenarsheev.cars;

import shenarsheev.RaceConst;

public class Car extends AbstractCar{

    public void driveCar() {
        //получение текущего положения и прибавление к нему
        //приращения
        int currentspot = getY() + RaceConst.velocity;
        //установка нового положения
        super.setY(currentspot);
    }
    //если совпали координаты - "авария"
    public boolean isCrash(PlayerCar pc){
        return (getX() == pc.getX() && getY() == pc.getCalculatedY())?
                true: false;
    }
}
