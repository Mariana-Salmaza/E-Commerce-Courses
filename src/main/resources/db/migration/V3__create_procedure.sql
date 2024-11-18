DELIMITER $$

CREATE OR REPLACE PROCEDURE RealizarCompra(
    IN id_user INT,
    IN p_id_curso INT,
    IN p_valor DECIMAL(10,2),
    IN p_id_forma INT
)
BEGIN
    DECLARE v_id_pedido INT;
    DECLARE v_id_pagamento INT;
    
    INSERT INTO pagamento (vl_pedido, dt_pagamento, status_pagamento)
    VALUES (p_valor, NOW(), 'pago');
    
    SET v_id_pagamento = LAST_INSERT_ID();
    
    INSERT INTO pedido (id_user, id_pag, pago, data_pedido)
    VALUES (id_user, v_id_pagamento, TRUE, NOW());
    
    -- Obter o id do pedido inserido
    SET v_id_pedido = LAST_INSERT_ID();
    
    INSERT INTO pedido_curso (id_pedido, id_curso, quantidade)
    VALUES (v_id_pedido, p_id_curso, 1);
    
    INSERT INTO pag_forma (id_pag, id_forma)
    VALUES (v_id_pagamento, p_id_forma);

    SELECT 'Pagamento realizado com sucesso. Pedido e pagamento registrados.' AS mensagem;
END$$

DELIMITER ;

CALL RealizarCompra(1, 2, 20.00, 1);
