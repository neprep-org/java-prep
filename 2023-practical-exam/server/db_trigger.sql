DELIMITER //
CREATE TRIGGER calculate_total_price
    BEFORE INSERT ON product_purchased
    FOR EACH ROW
BEGIN
    DECLARE base_price DOUBLE;
    DECLARE item_quantity INT;

    -- Retrieve the base price and quantity from product_quantity
    SELECT p.price, pq.quantity
    INTO base_price, item_quantity
    FROM products p
             JOIN product_quantity pq ON p.code = pq.product_code
    WHERE pq.id = NEW.quantity_id;

    -- Calculate the total price
    SET NEW.total = base_price * item_quantity;
END //
DELIMITER ;
