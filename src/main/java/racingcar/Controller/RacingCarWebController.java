package racingcar.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.Domain.RacingCar;
import racingcar.Domain.RacingCarGenetator;
import racingcar.Domain.RacingCars;
import racingcar.Domain.dto.GameInitializeDto;
import racingcar.Domain.dto.GameResultDto;
import racingcar.Service.MoveCar;

import java.util.Arrays;
import java.util.List;

public class RacingCarWebController {
    private final MoveCar moveCar;
    private final RacingCars racingCars;
    private RacingCarGenetator racingCarGenetator;

    public RacingCarWebController(MoveCar moveCar, RacingCars racingCars){
        this.moveCar = moveCar;
        this.racingCars = racingCars;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultDto> playRacingCar(
            @RequestBody GameInitializeDto gameInitializeDto) {
        String[] carNames = gameInitializeDto.getNames().split(",");
        List<String> carNameList = Arrays.asList(carNames);
        List<RacingCar> racingCarList = racingCarGenetator.generateRacingCars(carNameList);
    GameResultDto gameResultDto = moveCar.moveCars(racingCars);
    return ResponseEntity.ok().body(gameResultDto);
    }
}
