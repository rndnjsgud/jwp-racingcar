-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);
CREATE TABLE game
(
    game_id     BIGINT   NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (game_id)
);

CREATE TABLE player_position
(
    player_position_id BIGINT NOT NULL AUTO_INCREMENT,
    game_id            BIGINT NOT NULL,
    user_id            BIGINT NOT NULL,
    position           INT    NOT NULL,
    PRIMARY KEY (player_position_id)
);

CREATE TABLE game_winner
(
    game_winner_id BIGINT NOT NULL AUTO_INCREMENT,
    game_id        BIGINT NOT NULL,
    user_id        BIGINT NOT NULL,
    PRIMARY KEY (game_winner_id)
);