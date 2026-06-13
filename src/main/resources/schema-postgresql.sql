CREATE TABLE IF NOT EXISTS adotantes (
    id       SERIAL PRIMARY KEY,
    nome     VARCHAR(100) NOT NULL,
    cpf      VARCHAR(14)  NOT NULL UNIQUE,
    cep      VARCHAR(9)   NOT NULL,
    telefone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS gatos (
    id            SERIAL PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    sexo          VARCHAR(10)  NOT NULL,           -- MACHO, FEMEA
    faixa_etaria  VARCHAR(10)  NOT NULL,           -- FILHOTE, JOVEM, ADULTO, IDOSO
    cor           VARCHAR(50)  NOT NULL,
    tipo_pelagem  VARCHAR(50)  NOT NULL,
    comorbidades  VARCHAR(255),
    castrado      VARCHAR(5)   NOT NULL,           -- SIM, NAO
    disponivel    VARCHAR(5)   NOT NULL DEFAULT 'SIM',  -- SIM, NAO
    foto          VARCHAR(500)
);

ALTER TABLE gatos ADD COLUMN IF NOT EXISTS foto VARCHAR(500);
ALTER TABLE gatos ALTER COLUMN foto TYPE VARCHAR(500);

CREATE TABLE IF NOT EXISTS adocoes (
    id           SERIAL PRIMARY KEY,
    id_gato      INTEGER      NOT NULL REFERENCES gatos(id),
    id_adotante  INTEGER      NOT NULL REFERENCES adotantes(id),
    status       VARCHAR(20)  NOT NULL,            -- PENDENTE, APROVADA, CANCELADA
    data         DATE         NOT NULL
);
