package racingcar.Repository.Dao;

import racingcar.Repository.Entity.GameWinnerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryGameWinnerDao implements GameWinnerDao {
    final List<GameWinnerEntity> gameWinnerEntities = new ArrayList<>();


    @Override
    public long save(GameWinnerEntity gameWinnerEntity) {   //InMemeoryGameDao 보고 직접 구현
        final long gameId = gameWinnerEntity.getGameId();
        final long userId = gameWinnerEntity.getUserId();

        if(gameWinnerEntities.isEmpty()){
            final long id = 1L;
            gameWinnerEntities.add(new GameWinnerEntity(id, gameId, userId));
        }
        final int lastIndex = gameWinnerEntities.size()-1;
        final Long lastId = gameWinnerEntities.get(lastIndex).getId() + 1;
        gameWinnerEntities.add(new GameWinnerEntity(lastId,gameId,userId));
        return lastId;
    }

    @Override
    public List<GameWinnerEntity> findById(long gameId) {
        return gameWinnerEntities.stream()
                .filter(gameWinnerEntity -> gameWinnerEntity.getGameId() == gameId)
                .collect(Collectors.toList());
    }
}
