package racingcar.Domain.dto;

import racingcar.Domain.RacingCar;

import java.util.List;

public class GameResultResponse {
    private final String winners;
    private final List<RacingCar> racingCars;

    public GameResultResponse(final String winners, final List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public GameResultResponse(final List<String> winners, final List<RacingCar> racingCars) {
        this(String.join(",", winners), racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }
}
