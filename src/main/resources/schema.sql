CREATE TABLE director (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE actor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE genre (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE video (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    synopsis TEXT,
    year_of_release INT,
    running_time INT,
    is_active BOOLEAN DEFAULT TRUE,
    impressions INT DEFAULT 0,
    views INT DEFAULT 0
);

CREATE TABLE video_directors (
    video_id BIGINT NOT NULL,
    director_id BIGINT NOT NULL,
    PRIMARY KEY (video_id, director_id),
    FOREIGN KEY (video_id) REFERENCES video (id),
    FOREIGN KEY (director_id) REFERENCES director (id)
);

CREATE TABLE video_actor (
    video_id BIGINT NOT NULL,
    actor_id BIGINT NOT NULL,
    PRIMARY KEY (video_id, actor_id),
    FOREIGN KEY (video_id) REFERENCES video (id),
    FOREIGN KEY (actor_id) REFERENCES actor (id)
);

CREATE TABLE video_genres (
    video_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (video_id, genre_id),
    FOREIGN KEY (video_id) REFERENCES video (id),
    FOREIGN KEY (genre_id) REFERENCES genre (id)
);