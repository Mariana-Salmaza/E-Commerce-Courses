CREATE TABLE Forma_Pagamento (
    id_forma INT AUTO_INCREMENT PRIMARY KEY,
    nome_forma VARCHAR(50) NOT NULL,
    descricao TEXT
);

CREATE TABLE Pagamento (
    id_pag INT AUTO_INCREMENT PRIMARY KEY,
    vl_pedido DECIMAL(10, 2) NOT NULL,
    dt_pagamento DATETIME,
    status_pagamento VARCHAR(20) NOT NULL
);

CREATE TABLE Pag_Forma (
    id_pag INT,
    id_forma INT,
    PRIMARY KEY (id_pag, id_forma),
    FOREIGN KEY (id_pag) REFERENCES Pagamento(id_pag) ON DELETE CASCADE,
    FOREIGN KEY (id_forma) REFERENCES Forma_Pagamento(id_forma) ON DELETE CASCADE
);

CREATE TABLE User (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    nm_user VARCHAR(100) NOT NULL,
    email_user VARCHAR(100) UNIQUE NOT NULL,
    senha_user VARCHAR(255) NOT NULL
);

CREATE TABLE Curso (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    nm_curso VARCHAR(100) NOT NULL,
    vl_curso DECIMAL(10, 2) NOT NULL,
    descricao TEXT
);

CREATE TABLE Pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT,
    id_pag INT,
    pago BOOLEAN NOT NULL,
    data_pedido DATETIME NOT NULL,
    FOREIGN KEY (id_user) REFERENCES User(id_user) ON DELETE CASCADE,
    FOREIGN KEY (id_pag) REFERENCES Pagamento(id_pag) ON DELETE CASCADE
);

CREATE TABLE Pedido_Curso (
    id_curso INT,
    id_pedido INT,
    PRIMARY KEY (id_curso, id_pedido),
    quantidade INT NOT NULL,
    FOREIGN KEY (id_curso) REFERENCES Curso(id_curso) ON DELETE CASCADE,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido) ON DELETE CASCADE
);

CREATE TABLE Auditoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dt DATETIME NOT NULL,
    tabela_afetada VARCHAR(50) NOT NULL,
    acao VARCHAR(50) NOT NULL,
    mot VARCHAR(255),
    dados_anteriores TEXT,
    dados_novos TEXT
);
