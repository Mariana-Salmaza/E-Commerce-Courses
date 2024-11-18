DELIMITER $$

CREATE OR REPLACE TRIGGER trg_audit_valor_curso
AFTER UPDATE ON curso
FOR EACH ROW
BEGIN
    IF OLD.vl_curso <> NEW.vl_curso THEN
        INSERT INTO auditoria(id_curso, dt, valor_antigo, valor_novo, mot)
        VALUES(NEW.id_curso, NOW(), OLD.vl_curso, NEW.vl_curso, 'Alteração de valor do curso');
    END IF;
END$$

DELIMITER ;

