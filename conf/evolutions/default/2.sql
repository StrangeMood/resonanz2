# --- !Ups

CREATE SEQUENCE conversations_id_seq;
CREATE TABLE conversations (
    id integer PRIMARY KEY DEFAULT nextval('conversations_id_seq'),
    "key" varchar
);

# --- !Downs

DROP TABLE conversations;
DROP SEQUENCE conversations_id_seq;