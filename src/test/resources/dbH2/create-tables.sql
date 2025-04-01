CREATE TABLE ruolo
(
    ruolo_ID   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ruolo_nome VARCHAR(255) NOT NULL
);

CREATE TABLE utente
(
    utente_ID BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    CONSTRAINT email_UNIQUE UNIQUE (email)
);

CREATE TABLE cliente
(
    cliente_ID      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL,
    cognome         VARCHAR(255) NOT NULL,
    data_nascita    DATE         NOT NULL,
    cod_fiscale     VARCHAR(16)  NOT NULL,
    data_iscrizione DATE         NOT NULL,
    utente_ID       BIGINT       NULL,
    CONSTRAINT utente_cliente_ID FOREIGN KEY (utente_ID) REFERENCES utente (utente_ID)
);

CREATE TABLE veicolo
(
    veicolo_ID BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    num_targa  VARCHAR(7)   NOT NULL,
    modello    VARCHAR(255) NOT NULL,
    colore     VARCHAR(255) NOT NULL
);

CREATE TABLE autista
(
    autista_ID   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(255) NOT NULL,
    cognome      VARCHAR(255) NOT NULL,
    data_nascita DATE         NOT NULL,
    cod_fiscale  VARCHAR(16)  NOT NULL,
    veicolo_ID   BIGINT       NULL,
    utente_ID    BIGINT       NULL,
    CONSTRAINT utente_autista_ID FOREIGN KEY (utente_ID) REFERENCES utente (utente_ID),
    CONSTRAINT veicolo_ID FOREIGN KEY (veicolo_ID) REFERENCES veicolo (veicolo_ID)
);

CREATE TABLE utente_ruolo
(
    utente_ID BIGINT NOT NULL,
    ruolo_ID  BIGINT NOT NULL,
    PRIMARY KEY (utente_ID, ruolo_ID),
    CONSTRAINT utente_ruolo_ibfk_1 FOREIGN KEY (utente_ID) REFERENCES utente (utente_ID),
    CONSTRAINT utente_ruolo_ibfk_2 FOREIGN KEY (ruolo_ID) REFERENCES ruolo (ruolo_ID)
);

CREATE TABLE corsa
(
    corsa_ID          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    stato_corsa       ENUM ('STATO_COMPLETATA', 'STATO_IN_CORSO', 'STATO_ANNULLATA') NULL,
    distanza_percorsa DOUBLE         NOT NULL,
    costo_corsa       DOUBLE         NOT NULL,
    indirizzo_inizio  VARCHAR(255)   NOT NULL,
    indirizzo_fine    VARCHAR(255)   NOT NULL,
    data_prenotazione DATETIME       NOT NULL,
    data_inizio       DATETIME       NULL,
    data_fine         DATETIME       NULL,
    cliente_ID        BIGINT         NULL,
    autista_ID        BIGINT         NULL,
    CONSTRAINT autistaID FOREIGN KEY (autista_ID) REFERENCES autista (autista_ID),
    CONSTRAINT clienteID FOREIGN KEY (cliente_ID) REFERENCES cliente (cliente_ID)
);

CREATE INDEX ruolo_ID ON utente_ruolo (ruolo_ID);