DROP TABLE IF EXISTS compilation_events;
DROP TABLE IF EXISTS compilations;
DROP TABLE IF EXISTS participation_requests;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
     id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
     name VARCHAR(250) NOT NULL,
     email VARCHAR(254) NOT NULL,

     CONSTRAINT users_pk PRIMARY KEY (id),
     CONSTRAINT users_uq_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS categories (
     id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
     name VARCHAR(50) NOT NULL,

     CONSTRAINT categories_pk PRIMARY KEY (id),
     CONSTRAINT categories_uq_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS events (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(120) NOT NULL,
    annotation VARCHAR(2000) NOT NULL,
    description VARCHAR(7000) NOT NULL,
    category_id BIGINT NOT NULL,
    participant_limit INTEGER NOT NULL,
    state VARCHAR(32) NOT NULL,
    paid BOOLEAN NOT NULL,
    event_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    published_on TIMESTAMP WITHOUT TIME ZONE,
    user_id BIGINT NOT NULL,
    request_moderation BOOLEAN NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,

    CONSTRAINT events_pk PRIMARY KEY (id),
    CONSTRAINT events_fk_categories FOREIGN KEY (category_id) REFERENCES categories (id),
    CONSTRAINT events_fk_users FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS participation_requests (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    status VARCHAR(32),
    created_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT participation_requests_pk PRIMARY KEY (id),
    CONSTRAINT participation_requests_fk_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT participation_requests_fk_events FOREIGN KEY (event_id) REFERENCeS events (id),
    CONSTRAINT participation_requests_uq_user_per_event UNIQUE (user_id, event_id)
);

CREATE TABLE IF NOT EXISTS compilations (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(50) NOT NULL,
    pinned BOOLEAN NOT NULL,

    CONSTRAINT compilations_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS compilation_events (
    compilation_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,

    CONSTRAINT compilation_events_pk PRIMARY KEY (compilation_id, event_id),
    CONSTRAINT compilation_events_fk_compilations FOREIGN KEY (compilation_id) REFERENCES compilations (id),
    CONSTRAINT compilation_events_fk_events FOREIGN KEY (event_id) REFERENCES events (id)
)