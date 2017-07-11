--
-- File generated with SQLiteStudio v3.1.1 on ‹à 7 7 16:50:46 2017
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: tbl_category
DROP TABLE IF EXISTS tbl_category;

CREATE TABLE tbl_category (
    id            INTEGER PRIMARY KEY
                          NOT NULL,
    category_name TEXT
);


-- Table: tbl_speech
DROP TABLE IF EXISTS tbl_speech;

CREATE TABLE tbl_speech (
    id           INTEGER PRIMARY KEY
                         NOT NULL
                         UNIQUE,
    speech_name  TEXT,
    speech_date  TEXT,
    speech_place TEXT,
    speech_uri   TEXT,
    speech       TEXT,
    translation  TEXT,
    speecher_id  INTEGER CONSTRAINT constraint_speecher_id REFERENCES tbl_speecher (id),
    category_id  INTEGER CONSTRAINT constraint_category_id REFERENCES tbl_category (id) 
);


-- Table: tbl_speecher
DROP TABLE IF EXISTS tbl_speecher;

CREATE TABLE tbl_speecher (
    id                INTEGER PRIMARY KEY
                              NOT NULL
                              UNIQUE,
    speecher_name     TEXT    NOT NULL,
    speecher_birthday TEXT,
    speecher_disc     TEXT,
    speecher_country  TEXT,
    speecher_social   TEXT,
    speecher_photo    TEXT
);


-- Table: tlb_words
DROP TABLE IF EXISTS tlb_words;

CREATE TABLE tlb_words (
    id        INTEGER PRIMARY KEY
                      UNIQUE
                      NOT NULL,
    words     TEXT    NOT NULL,
    type      TEXT,
    ipa       TEXT,
    mean      TEXT,
    speech_id INTEGER CONSTRAINT constraint_speech_id REFERENCES tbl_speech (id) 
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
