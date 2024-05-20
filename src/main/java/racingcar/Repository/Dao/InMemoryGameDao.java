package racingcar.Repository.Dao;

import racingcar.Repository.Entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

public class InMemoryGameDao implements GameDao{
    private final List<GameEntity> gameEntities = new ArrayList<>();    //gameEntity List, final로 선언
    @Override
    public long save(GameEntity gameEntity) {   //gameEntity를 gameEntities에 저장
        final int trialCount = gameEntity.getTrialCount();  //해당 gameEntity의 TrialCount와 lastModifiedTime을 가져와 선언
        final String lastModifiedTime = gameEntity.getLastModifiedTime();

        if(gameEntities.isEmpty()){ //첫 gameEntity면 id 1로 부여 후 gameEntities에 저장
            final Long id = 1L;
            gameEntities.add(new GameEntity(id, trialCount, lastModifiedTime)); //새로운 GameEntity를 gameEntities에 저장
        }

        final int lastIndex = gameEntities.size()-1;    //gameEntities의 마지막 요소 가르키는 인덱스 선언
        final Long id = gameEntities.get(lastIndex).getId() + 1;    //gameEntities의 마지막 요소의 id + 1을 선언
        gameEntities.add(new GameEntity(id, trialCount, lastModifiedTime));
        return id;
    }

    @Override
    public List<GameEntity> findAll() {
        return gameEntities;    //전체 반환
    }
}
