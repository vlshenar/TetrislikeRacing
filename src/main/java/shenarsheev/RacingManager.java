package shenarsheev;

import shenarsheev.RaceView.RaceDisplay;
import shenarsheev.cars.Car;
import shenarsheev.cars.PlayerCar;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class RacingManager {
    //время прохода расстояние, равного длине корпуса машины
    private final int timegap = (int)(1 + RaceConst.carheight/RaceConst.velocity);
    private RaceDisplay display = null;
    private PlayerCar player = null;
    private Queue racingPark = null;
    private LinkedList<Car> onRoad = null;
    private ListIterator<Car> itCar = null;
    public RacingManager(RaceDisplay d){
        display = d;
        player = new PlayerCar();
        player.setY(500);
        racingPark = new LinkedList<Car>();
        onRoad = new LinkedList<Car>();
        for (int i = 0; i < 6; i++){
            racingPark.add(new Car());
        }
    }
    //получение ссылки для управления машиной игрока
    public PlayerCar getPlayer(){
        return player;
    }
    public boolean raceProcessing(){
        if (onRoad.isEmpty()){
            Car car = (Car)racingPark.poll();
            car.setX(choseLine());
            car.setY(0);
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
                    while (itCar.hasNext())
                    {
                        racingPark.offer(itCar.next());
                        itCar.remove();
                    }
                    return false;
                }
                if(car.getY() == 2*timegap*RaceConst.velocity){
                    Car nucar = (Car)racingPark.poll();
                    nucar.setX(choseLine()); nucar.setY(0);
                    nucar.driveCar();
                    itCar.add(nucar);
                }
                if (car.getY() >= 6*timegap*RaceConst.velocity){
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
