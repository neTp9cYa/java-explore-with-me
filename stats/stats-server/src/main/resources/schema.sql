CREATE TABLE IF NOT EXISTS hits (
     id int NOT NULL GENERATED ALWAYS AS IDENTITY,
     app varchar(30) NOT NULL,
     uri varchar(200) NOT NULL,
     ip varchar(15) NOT NULL,
     "timestamp" timestamp without time zone NOT NULL,
     CONSTRAINT hits_pk PRIMARY KEY (id)
);
