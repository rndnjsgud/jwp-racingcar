package racingcar.Repository.Dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import racingcar.Repository.Entity.PlayerEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

public class JdbcPlayerDao implements PlayerDao{
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PlayerEntity> actorRowMapper = (resultSet, rowNum) -> new PlayerEntity(
            resultSet.getLong("user_id"),
            resultSet.getString("name")
    );
    public JdbcPlayerDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(PlayerEntity playerEntity) {
        final String sql = "INSERT INTO player (name) VALUES(?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection ->{
                final PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, playerEntity.getName());
        return pstmt;
        },keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<PlayerEntity> findById(long id) {
        final String sql = "SELECT user_id, name FROM player WHERE user_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, actorRowMapper, id));
    }

    @Override
    public Optional<PlayerEntity> findByName(String name) {
        final String sql = "SELECT user_id, name FROM player WHERE name = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, actorRowMapper, name));
    }
}
