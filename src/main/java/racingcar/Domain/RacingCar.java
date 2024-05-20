package racingcar.Domain;

import java.util.HashMap;
import java.util.Map;

public class RacingCar {
    private String carName;
    private int carPosition;

    RacingCar(String carName){
        this.carName = carName;
        this.carPosition = 1;
    }

    public void moveCarPosition(){
        carPosition += 1;
    }

    public int getCarPosition(){
        return carPosition;
    }

    public String getCarName(){
        return carName;
    }
}
