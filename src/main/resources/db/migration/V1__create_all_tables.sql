CREATE TABLE forma_pagamento (
    id_forma INT AUTO_INCREMENT PRIMARY KEY,
    nome_forma VARCHAR(50) NOT NULL,
    descricao TEXT
);

CREATE TABLE pagamento (
    id_pag INT AUTO_INCREMENT PRIMARY KEY,
    vl_pedido DECIMAL(10, 2) NOT NULL,
    dt_pagamento DATETIME,
    status_pagamento VARCHAR(20) NOT NULL
);

CREATE TABLE pag_forma (
    id_pag INT,
    id_forma INT,
    PRIMARY KEY (id_pag, id_forma),
    FOREIGN KEY (id_pag) REFERENCES pagamento(id_pag) ON DELETE CASCADE,
    FOREIGN KEY (id_forma) REFERENCES forma_pagamento(id_forma) ON DELETE CASCADE
);

CREATE TABLE user (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    nm_user VARCHAR(100) NOT NULL,
    email_user VARCHAR(100) UNIQUE NOT NULL,
    data_nascimento DATE NOT NULL,
    phone VARCHAR(20) NOT NULL
);


CREATE TABLE curso (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    nm_curso VARCHAR(100) NOT NULL,
    vl_curso DECIMAL(10, 2) NOT NULL,
    descricao TEXT
);

CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT,
    id_pag INT,
    pago BOOLEAN NOT NULL,
    data_pedido DATETIME NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user(id_user) ON DELETE CASCADE,
    FOREIGN KEY (id_pag) REFERENCES pagamento(id_pag) ON DELETE CASCADE
);

CREATE TABLE pedido_curso (
    id_curso INT,
    id_pedido INT,
    PRIMARY KEY (id_curso, id_pedido),
    quantidade INT NOT NULL,
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso) ON DELETE CASCADE,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE CASCADE
);

CREATE TABLE auditoria (
    id_auditoria INT AUTO_INCREMENT PRIMARY KEY,
    id_curso INT NOT NULL,
    dt DATE NOT NULL,
    valor_antigo DECIMAL(10, 2),
    valor_novo DECIMAL(10, 2),
    mot VARCHAR(255),
    CONSTRAINT fk_auditoria FOREIGN KEY (id_curso) REFERENCES curso(id_curso)
);
