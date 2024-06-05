-- Directors
INSERT INTO director (name) VALUES ('Christopher Nolan');
INSERT INTO director (name) VALUES ('Steven Spielberg');
INSERT INTO director (name) VALUES ('Quentin Tarantino');

-- Actors
INSERT INTO actor (name) VALUES ('Leonardo DiCaprio');
INSERT INTO actor (name) VALUES ('Joseph Gordon-Levitt');
INSERT INTO actor (name) VALUES ('Elliot Page');
INSERT INTO actor (name) VALUES ('Brad Pitt');
INSERT INTO actor (name) VALUES ('Samuel L. Jackson');
INSERT INTO actor (name) VALUES ('Uma Thurman');
INSERT INTO actor (name) VALUES ('Matthew McConaughey');
INSERT INTO actor (name) VALUES ('Anne Hathaway');

-- Genres
INSERT INTO genre (name) VALUES ('Science Fiction');
INSERT INTO genre (name) VALUES ('Drama');
INSERT INTO genre (name) VALUES ('Action');
INSERT INTO genre (name) VALUES ('Adventure');
INSERT INTO genre (name) VALUES ('Thriller');

-- Videos
INSERT INTO video (title, synopsis, year_of_release, running_time, is_active, impressions, views) VALUES
    ('Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.', 2010, 148, true, 100, 300),
    ('Pulp Fiction', 'The lives of two mob hitmen, a boxer, a gangster, and his wife intertwine in four tales of violence and redemption.', 1994, 154, true, 10, 20),
    ('Jurassic Park', 'During a preview tour, a theme park suffers a major power breakdown that allows its cloned dinosaur exhibits to run amok.', 1993, 127, true, 0, 0),
    ('Interstellar', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity’s survival.', 2014, 169, true, 150, 170);

-- Mapping tables for Inception
INSERT INTO video_directors (video_id, director_id) VALUES (1, 1);
INSERT INTO video_actor (video_id, actor_id) VALUES (1, 1);
INSERT INTO video_actor (video_id, actor_id) VALUES (1, 2);
INSERT INTO video_actor (video_id, actor_id) VALUES (1, 3);
INSERT INTO video_genres (video_id, genre_id) VALUES (1, 1);

-- Mapping tables for Pulp Fiction
INSERT INTO video_directors (video_id, director_id) VALUES (2, 3);
INSERT INTO video_actor (video_id, actor_id) VALUES (2, 4);
INSERT INTO video_actor (video_id, actor_id) VALUES (2, 5);
INSERT INTO video_actor (video_id, actor_id) VALUES (2, 6);
INSERT INTO video_genres (video_id, genre_id) VALUES (2, 3);
INSERT INTO video_genres (video_id, genre_id) VALUES (2, 5);

-- Mapping tables for Jurassic Park
INSERT INTO video_directors (video_id, director_id) VALUES (3, 2);
INSERT INTO video_genres (video_id, genre_id) VALUES (3, 4);
INSERT INTO video_genres (video_id, genre_id) VALUES (3, 5);

-- Mapping tables for Interstellar
INSERT INTO video_directors (video_id, director_id) VALUES (4, 1);
INSERT INTO video_actor (video_id, actor_id) VALUES (4, 7);
INSERT INTO video_actor (video_id, actor_id) VALUES (4, 8);
INSERT INTO video_genres (video_id, genre_id) VALUES (4, 1);
INSERT INTO video_genres (video_id, genre_id) VALUES (4, 4);