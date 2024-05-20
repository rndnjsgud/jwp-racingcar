package racingcar.Service;

import racingcar.Domain.RacingCar;
import racingcar.Domain.RacingCars;
import racingcar.Domain.dto.GameResultDto;
import racingcar.View.Output;

import java.util.ArrayList;
import java.util.List;

public class MoveCar {
    public int generateRandomNumber(){
        return (int)(Math.random()*10);
    }

    public void moveCar(RacingCar racingCar){
        if(generateRandomNumber() >= 4){
            racingCar.moveCarPosition();
        }
    }

    public GameResultDto moveCars(RacingCars racingCars){
       List<RacingCar> racingCarList = racingCars.getRacingCars();
       int winnersPosition = Output.findWinnersPosition(racingCars);
       List<String> winners = new ArrayList<>();
        for(int i = 0 ; i < racingCarList.size() ; i++){
            if(racingCarList.get(i).getCarPosition() == winnersPosition){
                winners.add(racingCarList.get(i).getCarName());
            }
        }
       racingCarList.stream()
               .forEach(this::moveCar);

       //return racingCarList;
        return new GameResultDto(winners, racingCarList);
    }
}
