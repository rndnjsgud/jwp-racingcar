package racingcar.Repository.Dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.Repository.Entity.GameEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class jdbcGameDao implements GameDao{

    private final JdbcTemplate jdbcTemplate;    //JdbcTemplate 선언
    private final RowMapper<GameEntity> actorRowMapper = (resultSet, rowNum) -> new GameEntity(
            resultSet.getLong("game_id"),
            resultSet.getInt("trial_count"),
            resultSet.getString("created_at")
    );  //RowMapper를 통해 GameEntity의 속성을 각 속성 명을 통해 연결

    public jdbcGameDao(final DataSource dataSource) { //DataSource는 데이터베이스 연결을 관리하는 인터페이스
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final GameEntity gameEntity) {
        final String sql = "INSERT INTO game (trial_count) VALUES (?)";
        //game이라는 테이블에 insert, (trial_count) 는 삽입할 열 이름(값), (?)는 실제 입력될 값을 나타냄(PlaceHolder)
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();  //삽입 시 자동 생성된 키 값을 가짐
        jdbcTemplate.update(connection -> { //update는 삽입, 수정 시 사용, PreparedStatement와 KeyHolder를 매개변수로 가짐
            final PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Connection 객체는 DB와의 연결을 관리
            //pstmt는 기존 작성된 sql을 사용, Statement.RETURN_GENERATED_KEYS는 삽입된 행의 키 값을 반환
            pstmt.setInt(1, gameEntity.getTrialCount());    //sql의 매개변수 ?(첫 번째 인덱스의 변수)에 gameEntity의 trialcount를 대입
            return pstmt;   //객체 반환하여 connection의 첫 번째 매개변수 전달
        }, keyHolder);
        return (long) Objects.requireNonNull(keyHolder.getKeys()).get("GAME_ID");
    }

    @Override
    public List<GameEntity> findAll() {
        final String sql = "SELECT game_id, trial_count, created_at FROM game";
        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
