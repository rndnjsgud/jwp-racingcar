package racingcar.Repository.Dao;

import racingcar.Repository.Entity.PlayerPositionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemeoryPlayerPositionDao implements PlayerPositionDao{   //직접 구현
    private final List<PlayerPositionEntity> playerPositionEntities = new ArrayList<>();

    @Override
    public long save(PlayerPositionEntity playerPositionEntity) {
        final Long gameId = playerPositionEntity.getGameId();
        final Long userId = playerPositionEntity.getUserId();
        final int position = playerPositionEntity.getPosition();

        if(playerPositionEntities.isEmpty()){
            final long id = 1L;
            playerPositionEntities.add(new PlayerPositionEntity(id,gameId,userId,position));
            return id;
        }
        final int lastIndex = playerPositionEntities.size()-1;
        final long lastId = playerPositionEntities.get(lastIndex).getId()+1;

        playerPositionEntities.add(new PlayerPositionEntity(lastId,gameId,userId,position));
        return lastId;
    }

    @Override
    public List<PlayerPositionEntity> findByGameId(long gameId) {
        return playerPositionEntities.stream()
                .filter(playerPositionEntity -> playerPositionEntity.getGameId() == gameId)
                .collect(Collectors.toList());
    }
}
