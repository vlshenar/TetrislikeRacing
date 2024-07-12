package shenarsheev;

import shenarsheev.RaceView.RaceDisplay;
import shenarsheev.cars.Car;
import shenarsheev.cars.PlayerCar;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
/**
 * В паттерне Модель-Представление-Контроллер
 * данный класс вместе с пакетом cars реализует компоненту Модель
 */
public class RacingManager {
    //время прохода расстояние, равного длине корпуса машины
    private final int timegap = 1 + RaceConst.carheight/RaceConst.velocity;
    private RaceDisplay display;
    private PlayerCar player;
    //все участвующие в игре машины
    private Queue racingPark;
    //машины, "едущие по дороге"
    private LinkedList<Car> onRoad;
    private ListIterator<Car> itCar = null;
    public RacingManager(RaceDisplay d){
        display = d;
        player = new PlayerCar();
        player.setY(500);
        racingPark = new LinkedList<Car>();
        onRoad = new LinkedList<>();
        for (int i = 0; i < 7; i++){
            racingPark.add(new Car());
        }
    }
    //получение ссылки для управления машиной игрока
    public PlayerCar getPlayer(){
        return player;
    }
    //организует весь процесс игры: выпускает новые машины на дорогу
    //убирает с дороги вышедшие за пределы экрана
    //возвращает false, если произошло столкновение с машиной игрока
    public boolean raceProcessing(){
        //начало игры - трасса еще пуста
        if (onRoad.isEmpty()){
            Car car = (Car)racingPark.poll();
            car.setX(choseLine());
            car.setY(0); car.setMajor(true);
            onRoad.add(car);
            itCar = onRoad.listIterator();
            itCar.next().driveCar();
            car = itCar.previous();
            display.setCarXY(0, car.getX(), car.getY());
        }
        else
        {
            while(itCar.hasNext()){
                Car car = itCar.next();
                if(car.isCrash(player)){
                    itCar = onRoad.listIterator();
                    //проверка условия столкновения
                    while (itCar.hasNext())
                    {
                        racingPark.offer(itCar.next());
                        itCar.remove();
                    }
                    return false;
                }
                //добавляет новую машину
                if(car.getY() == 2*timegap*RaceConst.velocity && car.isMajor()){
                    //первая (главная) машина в паре
                    Car majorcar = (Car)racingPark.poll();
                    majorcar.setX(choseLine()); majorcar.setY(0);
                    majorcar.driveCar(); majorcar.setMajor(true);
                    //вторая машина в паре
                    Car secondcar = (Car)racingPark.poll();
                    secondcar.setX(choseLine()); secondcar.setY(0);
                    secondcar.driveCar(); secondcar.setMajor(false);
                    itCar.add(majorcar); itCar.add(secondcar);
                }
                //удаляет уехавшую из области видимости пользователя машину
                if (car.getY() >= 6*timegap*RaceConst.velocity ){
                    racingPark.offer(car);
                    itCar.remove();
                }
                car.driveCar();
            }
            //заполнить все слоты дисплея
            int i =0;
            while (itCar.hasPrevious()){
                Car car = itCar.previous();
                display.setCarXY(i, car.getX(), car.getY());
                i++;
            }
        }
        display.setPlayerX(player.getX());
        display.setPlayerY(player.getY());
        display.repaint();
        return  true;
    }
    //выбор дорожки для новой машины
    private int choseLine(){
        int rand = ((int)(Math.random()*1000))%5;
        return RaceConst.carwidth*rand;
    }
}
