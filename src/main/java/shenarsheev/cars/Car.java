package shenarsheev.cars;

import shenarsheev.RaceConst;

public class Car extends AbstractCar{
    //время пролета расстояния, равного длине корпуса машины
    //необходима для выполнения условия добавления новой машины в
    //RacingManager.raceProcessing()
    private final int timegap = 1 + RaceConst.carheight/RaceConst.velocity;
    //ускорение
    private static int boost = 0;
    //устанавливает величину ускорения
    public static void setBoost(boolean b){
        if(b == true) boost = RaceConst.velocity;
        else boost = 0;
    }
    public static int getBoost(){
        return boost;
    }
    //"двигает" машину по "дороге"
    public void driveCar() {
        //ловит значение, необходимое для добавления новой машины на дорогу
        if (getY() == (2*timegap - 1)*RaceConst.velocity && boost == RaceConst.velocity){
            setY(2*timegap*RaceConst.velocity);
        }
        else{
        //получение текущего положения и прибавление к нему
        //приращения
        int currentspot = getY() + RaceConst.velocity + boost;
        //установка нового положения
        setY(currentspot);
        }
    }
    //если совпали координаты - "авария"
    public boolean isCrash(PlayerCar pc){
        return (getX() == pc.getX() && getY() == pc.getCalculatedY())?
                true: false;
    }
}
