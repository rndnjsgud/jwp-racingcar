package racingcar.Repository.Dao;

import racingcar.Repository.Entity.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryPlayerDao implements PlayerDao{    //타 Dao 구현방식 보고 직접 구현
    private final List<PlayerEntity> playerEntities = new ArrayList<>();
    @Override
    public long save(PlayerEntity playerEntity) {
        final String name = playerEntity.getName();

        if(playerEntities.isEmpty()){
            final long id = 0L;
            playerEntities.add(new PlayerEntity(id,name));
            return id;
        }

        final int lastIndex = playerEntities.size()-1;
        final long lastId = playerEntities.get(lastIndex).getId()+1;

        playerEntities.add(new PlayerEntity(lastId, name));
        return lastId;
    }

    @Override
    public Optional<PlayerEntity> findById(long id) {
        return playerEntities.stream()
                .filter(playerEntity -> playerEntity.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<PlayerEntity> findByName(String name) {
        return playerEntities.stream()
                .filter(playerEntity -> playerEntity.getName().equals(name))
                .findFirst();
    }
}
