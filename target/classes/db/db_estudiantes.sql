CREATE DATABASE IF NOT EXISTS colegioDB;
USE colegioDB;

CREATE TABLE estudiante (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    nom_estudiante VARCHAR(100) NOT NULL,
    email_estudiante VARCHAR(150) NOT NULL UNIQUE
);


CREATE TABLE curso (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150),
    descripcion TEXT
);


CREATE TABLE estudiante_curso (
    id_estudiante INT NOT NULL,
    id_curso INT NOT NULL,

    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante)
        ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (id_curso) REFERENCES curso(id_curso)
        ON DELETE CASCADE ON UPDATE CASCADE
);