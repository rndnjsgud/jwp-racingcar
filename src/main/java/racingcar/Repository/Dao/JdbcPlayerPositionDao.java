package racingcar.Repository.Dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import racingcar.Repository.Entity.PlayerPositionEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class JdbcPlayerPositionDao implements PlayerPositionDao{
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PlayerPositionEntity> actorRowMapper = (resultSet, rowNum)-> new PlayerPositionEntity(
        resultSet.getLong("player_position_id"),
        resultSet.getLong("game_id"),
        resultSet.getLong("user_id"),
        resultSet.getInt("position")
    );

    public JdbcPlayerPositionDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(PlayerPositionEntity playerPositionEntity) {
        String sql = "INSERT INTO player_position (game_id, user_id, position) VALUES (?,?,?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection ->{
            PreparedStatement pstmt = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, playerPositionEntity.getGameId());
            pstmt.setLong(2, playerPositionEntity.getUserId());
            pstmt.setLong(3, playerPositionEntity.getPosition());
            return pstmt;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<PlayerPositionEntity> findByGameId(long gameId) {
        String sql = "SELECT game_id, user_id, position "
                +"FROM player_position WHERE "
                +"WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper,gameId);  //queryForObject는 결과를 리스트로, query는 하나의 결과
    }
}
