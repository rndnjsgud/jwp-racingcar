package racingcar.Repository.Dao;

import racingcar.Repository.Entity.GameWinnerEntity;

import java.util.List;

public interface GameWinnerDao {    //gameWinnerEntity관련 인터페이스
    long save (GameWinnerEntity gameWinnerEntity);  //gameWinnerEntity저장 메소드
    List<GameWinnerEntity> findById(final long gameId); //id로 gameWinnerEntity찾는 메소드
}
