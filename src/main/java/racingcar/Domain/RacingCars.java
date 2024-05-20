package racingcar.Domain;

import java.util.List;
import java.util.Map;

public class RacingCars {
    private final List<RacingCar> racingCars;

    public RacingCars (List<RacingCar> racingCars){
        this.racingCars = racingCars;
    }

    public List<RacingCar> getRacingCars(){
        return racingCars;
    }
}
