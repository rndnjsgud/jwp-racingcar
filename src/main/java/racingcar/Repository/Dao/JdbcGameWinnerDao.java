package racingcar.Repository.Dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.Repository.Entity.GameWinnerEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcGameWinnerDao implements GameWinnerDao{
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<GameWinnerEntity> actorRowMapper = (resultSet, rowNum) -> new GameWinnerEntity(
            resultSet.getLong("game_winner_id"),
            resultSet.getLong("game_id"),
            resultSet.getLong("user_id")
    );

    public JdbcGameWinnerDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(GameWinnerEntity gameWinnerEntity) {
        final String sql = "INSERT INTO game_winner (game_id, user_id) VALUES(?,?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, gameWinnerEntity.getGameId());
            pstmt.setLong(2, gameWinnerEntity.getUserId());
            return pstmt;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<GameWinnerEntity> findById(long gameId) {
        final String sql = "SELECT game_winner_id, game_id, user_id "
                + "FROM game_winner "
                + "WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
