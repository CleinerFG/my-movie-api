-- Join Table: Movie-Streaming (Many-to-Many)

CREATE TABLE movie_streaming
(
    movie_id     BIGINT NOT NULL,
    streaming_id BIGINT NOT NULL,

    -- Composite PK:  to ensure uniqueness and performance
    PRIMARY KEY (movie_id, streaming_id),

    -- Referential integrity: constraints
    CONSTRAINT fk_movie_streaming_movie
        FOREIGN KEY (movie_id)
            REFERENCES movie (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_movie_streaming_streaming
        FOREIGN KEY (streaming_id)
            REFERENCES streaming (id)
            ON DELETE CASCADE
);