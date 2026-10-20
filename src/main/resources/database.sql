-- Código para criação de banco de dados SQLite.
-- SQLite foi escolhido por ser mais facil de testar, apresentar, e validar.
-- O mais correto para esse tipo de aplicação seria usar um Postgress, MySQL ou MariaDB.

CREATE TABLE IF NOT EXISTS user (
    id CHAR(36) PRIMARY KEY,
    name TEXT NOT NULL,
    cpf TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL,
    password_hash TEXT NOT NULL,
    type TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS doctor (
    id CHAR(36) PRIMARY KEY REFERENCES user(id),
    crm TEXT NOT NULL UNIQUE,
    specialty TEXT
);

CREATE TABLE IF NOT EXISTS patient (
    id CHAR(36) PRIMARY KEY REFERENCES user(id),
    birth_date TEXT,
    health_insurance TEXT
);

CREATE TABLE IF NOT EXISTS appointment (
    id CHAR(36) PRIMARY KEY,
    patient_id CHAR(36) NOT NULL REFERENCES patient(id),
    doctor_id CHAR(36) NOT NULL REFERENCES doctor(id),
    date_time TEXT NOT NULL,
    status TEXT NOT NULL,
    notes TEXT
);