package racingcar.Repository.Dao;

import racingcar.Repository.Entity.PlayerEntity;

import java.util.Optional;

public interface PlayerDao {
    long save(final PlayerEntity playerEntity);

    Optional<PlayerEntity> findById(final long id);

    Optional<PlayerEntity> findByName(final String name);
}
