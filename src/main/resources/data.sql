CREATE TABLE tb_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO tb_users (username, password)
VALUES ('username', '$2a$10$KEfGCrJ1hYN01duFh2iasuTFDOYmJuHJC.Mf8.TOE66HViKdy.X/S');
