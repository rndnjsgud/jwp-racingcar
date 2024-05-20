package racingcar.Repository.Dao;

import racingcar.Repository.Entity.GameEntity;

import java.util.List;

public interface GameDao {  //gameEntity관련 인터페이스
    long save(final GameEntity gameEntity); //gameEntity저장 인터페이스 메소드
    List<GameEntity> findAll(); //gameEntity를 모두 불러오는 인터페이스 메소드
}
