USE gasolinera_fase1;

DROP TRIGGER IF EXISTS trg_producto_historial_precio;
DROP TRIGGER IF EXISTS trg_empleado_no_autosupervision_insert;
DROP TRIGGER IF EXISTS trg_empleado_no_autosupervision_update;

DELIMITER //
CREATE TRIGGER trg_empleado_no_autosupervision_insert
AFTER INSERT ON empleado
FOR EACH ROW
BEGIN
    IF NEW.id_supervisor = NEW.id_empleado THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Un empleado no puede supervisarse a sí mismo';
    END IF;
END//

CREATE TRIGGER trg_empleado_no_autosupervision_update
BEFORE UPDATE ON empleado
FOR EACH ROW
BEGIN
    IF NEW.id_supervisor = NEW.id_empleado THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Un empleado no puede supervisarse a sí mismo';
    END IF;
END//

CREATE TRIGGER trg_producto_historial_precio
AFTER UPDATE ON producto
FOR EACH ROW
BEGIN
    IF NOT (OLD.precio_venta <=> NEW.precio_venta) THEN
        INSERT INTO historial_precio
            (id_producto, precio_anterior, precio_nuevo, fecha_hora)
        VALUES
            (NEW.id_producto, OLD.precio_venta, NEW.precio_venta, CURRENT_TIMESTAMP);
    END IF;
END//
DELIMITER ;
