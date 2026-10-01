CREATE TABLE practice_schedule (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    active BOOLEAN NOT NULL,

    CONSTRAINT fk_practice_schedule_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);



CREATE TABLE practice_days (
    practice_schedule_id BIGINT NOT NULL,
    day_of_week VARCHAR(20) NOT NULL,
    time TIME NOT NULL,
    duration INTEGER NOT NULL,

    CONSTRAINT fk_practice_days_schedule
        FOREIGN KEY (practice_schedule_id)
        REFERENCES practice_schedule(id),

    CONSTRAINT uk_practice_days_schedule_time
        UNIQUE (practice_schedule_id, day_of_week, time)
);