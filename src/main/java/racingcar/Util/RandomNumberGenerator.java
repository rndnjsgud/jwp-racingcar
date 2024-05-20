package racingcar.Util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator implements NumberGenerator{
    @Override
    public int generate() {
        return (int) (Math.random() * 10);
    }
}
