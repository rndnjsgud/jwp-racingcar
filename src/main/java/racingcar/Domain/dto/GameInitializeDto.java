package racingcar.Domain.dto;

public class GameInitializeDto {

    private String names;
    private int count;

    public GameInitializeDto(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}