CREATE TABLE practice_sessions (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    scheduled_at TIMESTAMP NOT NULL,
    started_at TIMESTAMP,
    ended_at TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    practice_schedule_id BIGINT NOT NULL,

    CONSTRAINT fk_practice_sessions_user
                               FOREIGN KEY (user_id)
                               REFERENCES users(id),

    CONSTRAINT fk_practice_sessions_schedule
                               FOREIGN KEY (practice_schedule_id)
                               REFERENCES practice_schedule(id)

);