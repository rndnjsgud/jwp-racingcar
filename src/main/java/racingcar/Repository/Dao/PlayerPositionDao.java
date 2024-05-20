package racingcar.Repository.Dao;

import racingcar.Repository.Entity.PlayerPositionEntity;

import java.util.List;

public interface PlayerPositionDao {
    long save(PlayerPositionEntity playerPositionEntity);

    List<PlayerPositionEntity> findByGameId(final long gameId);

}
