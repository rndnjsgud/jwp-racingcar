package racingcar.View;

import racingcar.Domain.RacingCar;
import racingcar.Domain.RacingCars;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {
    private static final String INPUT_CAR_NAME = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분";
    private static final String INPUT_GAME_NUMBER = "시도할 횟수는 몇 회인가요?";

    public static List<String> inputCarName(){ //리스트로 차 이름 받은 후 controller에서 Racingcars 생성
        System.out.println(INPUT_CAR_NAME);
        Scanner sc = new Scanner(System.in);
        List<String> carNames = Arrays.stream(sc.next()
                .split(","))
                .collect(Collectors.toList());
        return carNames;
    }

    public static int inputGameNumber(){
        System.out.println(INPUT_GAME_NUMBER);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
