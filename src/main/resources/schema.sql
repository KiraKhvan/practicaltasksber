CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL        COMMENT 'Служебное поле hibernate',
    name        VARCHAR(50) NOT NULL    COMMENT 'Наименование',
    full_name   VARCHAR(200) NOT NULL   COMMENT 'Полное наименование',
    kpp         INTEGER  NOT NULL       COMMENT 'КПП',
    address     VARCHAR(2000) NOT NULL  COMMENT 'Адрес',
    phone       VARCHAR(20)             COMMENT 'Номер телефона',
    inn         INTEGER  NOT NULL       COMMENT 'ИНН',
    is_active   BOOLEAN                 COMMENT 'Организация активна'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
    id                  INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER NOT NULL        COMMENT 'Служебное поле hibernate',
    name                VARCHAR(50)             COMMENT 'Наименование',
    address             VARCHAR(2000)           COMMENT 'Адрес',
    phone               VARCHAR(20)             COMMENT 'Номер телефона',
    is_active           BOOLEAN                 COMMENT 'Офис активен',
    organization_id     INTEGER NOT NULL        COMMENT 'Организация'
);
COMMENT ON TABLE Office IS 'Офис';

ALTER TABLE Office ADD FOREIGN KEY (organization_id) REFERENCES Organization(id);

CREATE TABLE IF NOT EXISTS Country (
    id          INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL        COMMENT 'Служебное поле hibernate',
    name        VARCHAR(50)             COMMENT 'Наименование',
    code_        VARCHAR(10)             COMMENT 'Код страны'
);
COMMENT ON TABLE Country IS 'Страна';

CREATE TABLE IF NOT EXISTS Document_Type (
    id          INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL        COMMENT 'Служебное поле hibernate',
    name        VARCHAR(50)             COMMENT 'Наименование',
    code_       VARCHAR(10)             COMMENT 'Код типа документа'
);
COMMENT ON TABLE Document_Type IS 'Тип документа';

CREATE TABLE IF NOT EXISTS Position_ (
    id          INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL        COMMENT 'Служебное поле hibernate',
    name        VARCHAR(50) NOT NULL    COMMENT 'Наименование'
);
COMMENT ON TABLE Position_ IS 'Должность';

CREATE TABLE IF NOT EXISTS User_ (
    id              INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER NOT NULL        COMMENT 'Служебное поле hibernate',
    office_id       INTEGER NOT NULL        COMMENT 'Офис',
    first_name      VARCHAR(50) NOT NULL    COMMENT 'Имя',
    last_name       VARCHAR(50)             COMMENT 'Фамилия',
    middle_name     VARCHAR(50)             COMMENT 'Отчество',
    phone           VARCHAR(20)             COMMENT 'Номер телефона',
    position_id     INTEGER NOT NULL        COMMENT 'Должность',
    is_identified   BOOLEAN                 COMMENT 'Пользователь идентифицируется'
);
COMMENT ON TABLE User_ IS 'Пользователь';

ALTER TABLE User_ ADD FOREIGN KEY (office_id) REFERENCES Office(id);
ALTER TABLE User_ ADD FOREIGN KEY (position_id) REFERENCES Position_(id);

CREATE TABLE IF NOT EXISTS Document (
    id                  INTEGER                     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER NOT NULL            COMMENT 'Служебное поле hibernate',
    document_type_id    INTEGER                     COMMENT 'Тип документа',
    number_             INTEGER                     COMMENT 'Номер документа',
    code_               VARCHAR(50)                 COMMENT 'Код документа',
    date_               TIMESTAMP WITHOUT TIME ZONE COMMENT 'Дата документа',
    country_id          INTEGER                     COMMENT 'Страна',
    user_id             INTEGER NOT NULL            COMMENT 'Пользователь'
);
COMMENT ON TABLE Document IS 'Документ';

ALTER TABLE Document ADD FOREIGN KEY (document_type_id) REFERENCES Document_Type(id);
ALTER TABLE Document ADD FOREIGN KEY (country_id) REFERENCES Country(id);
ALTER TABLE Document ADD FOREIGN KEY (user_id) REFERENCES User_(id);
