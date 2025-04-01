-- Ruolo
INSERT INTO ruolo (ruolo_ID, ruolo_nome)
VALUES
    (1,'ADMIN'),
    (2, 'USER'),
    (3, 'MODIFY_AUTISTA'),
    (4, 'MODIFY_CLIENTE'),
    (5, 'MODIFY_CORSA'),
    (6, 'MODIFY_RUOLO'),
    (7, 'MODIFY_VEICOLO');

-- Utente
INSERT INTO utente (utente_ID, email, password)
VALUES
    (1, 'email01@test.com', 'password'),
    (2, 'email02@test.com', 'password'),
    (3, 'email03@test.com', 'password'),
    (4, 'email04@test.com', 'password');

-- Veicolo
INSERT INTO veicolo (veicolo_ID, num_targa, modello, colore)
VALUES
    (1, 'AB123CD', 'Fiat Panda', 'Bianco'),
    (2, 'EF456GH', 'Toyota Corolla', 'Azzurro'),
    (3, 'IJ789LM', 'Volkswagen Golf', 'Grigio');

-- Cliente
INSERT INTO cliente (cliente_ID, nome, cognome, data_nascita, cod_fiscale, data_iscrizione, utente_ID)
VALUES
    (1, 'nome01', 'cognome01', '1999-12-01', 'BIGDIH69E69S111X', '2024-01-01', 1),
    (2, 'nome02', 'cognome02', '1999-12-02', 'GLUEEE88E20L222X', '2024-01-02', 2);

-- Autista
INSERT INTO autista (autista_ID, nome, cognome, data_nascita, cod_fiscale, veicolo_ID, utente_ID)
VALUES
    (1, 'nome03', 'cognome03', '1999-12-03', 'GUIDOO11K88K333X', 1, 3),
    (2, 'nome04', 'cognome04', '1999-12-04', 'SIVOLA13O40K444X', 2, 4);

-- Corsa
INSERT INTO corsa (corsa_ID, stato_corsa, distanza_percorsa, costo_corsa, indirizzo_inizio, indirizzo_fine,
                   data_prenotazione, data_inizio, data_fine, cliente_ID, autista_ID)
VALUES
    (1, 'STATO_COMPLETATA', 200.0, 60.00, 'Via test 1', 'Via test 2',
     '2024-02-1 09:00:00', '2024-02-1 09:10:00', '2024-02-1 09:30:00', 1, 1),
    (2, 'STATO_COMPLETATA', 520.0, 130.00, 'Via test 1', 'Via test 3',
     '2024-02-2 13:00:00', '2024-02-2 13:30:00', '2024-02-2 15:20:00', 2, 2);

-- UtenteRuolo
INSERT INTO utente_ruolo (utente_ID, ruolo_ID)
VALUES
    (1, 1), -- ADMIN

    (2, 2), -- USER
    (2, 4), -- MODIFY_CLIENTE
    (2, 5), -- MODIFY_CORSA
    (2, 6), -- MODIFY_RUOLO
    (2, 7), -- MODIFY_VEICOLO

    (3, 2),  -- USER
    (3, 3),  -- MODIFY_AUTISTA

    (4, 2),  -- USER
    (4, 3);  -- MODIFY_AUTISTA