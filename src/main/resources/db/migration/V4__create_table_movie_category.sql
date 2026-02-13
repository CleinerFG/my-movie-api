-- Join Table: Movie-Category (Many-to-Many)

CREATE TABLE movie_category
(
    movie_id    BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    -- Composite PK:  to ensure uniqueness and performance
    PRIMARY KEY (movie_id, category_id),

    -- Referential integrity: constraints
    CONSTRAINT fk_movie_category_movie
        FOREIGN KEY (movie_id)
            REFERENCES movie (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_movie_category_category
        FOREIGN KEY (category_id)
            REFERENCES category (id)
            ON DELETE CASCADE
);
