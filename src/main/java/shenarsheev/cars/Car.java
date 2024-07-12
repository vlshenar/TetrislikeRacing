package shenarsheev.cars;

import shenarsheev.RaceConst;

public class Car extends AbstractCar{
    //маркировка главной машины в паре
    private boolean major;
    // определение машины как главной или подчиненной
    public void setMajor(boolean major) {
        this.major = major;
    }

    public boolean isMajor() {
        return major;
    }

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
        //определяет эффективную длину взаимодействия машин
        int cargolenght = 3*RaceConst.carheight/4;
        return (getX() == pc.getX() && getY() + cargolenght >= pc.getCalculatedY()
                && getY() <= pc.getCalculatedY() + cargolenght)?
                true: false;
    }
}
