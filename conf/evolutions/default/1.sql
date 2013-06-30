# --- !Ups

CREATE SEQUENCE users_id_seq;
CREATE TABLE users (
    id integer PRIMARY KEY DEFAULT nextval('users_id_seq'),
    name varchar
);

# --- !Downs

DROP TABLE users;
DROP SEQUENCE users_id_seq;