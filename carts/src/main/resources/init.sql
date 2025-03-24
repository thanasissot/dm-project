-- Create tables for sotiroglou.athanasios.microservices.cartsq.Cart and sotiroglou.athanasios.microservices.cartsq.CartItem entities

-- First, create the carts table
CREATE TABLE IF NOT EXISTS carts (
                                     id BIGSERIAL PRIMARY KEY,
                                     customer_id VARCHAR(255) NOT NULL
    );

-- Then, create the cart_items table with foreign key reference to carts
CREATE TABLE IF NOT EXISTS cart_items (
                                          id BIGSERIAL PRIMARY KEY,
                                          cart_id BIGINT NOT NULL,
                                          quantity INT NOT NULL,
                                          product_id VARCHAR(255) NOT NULL,
    CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE
    );

-- Create an index on cart_id for better query performance
CREATE INDEX IF NOT EXISTS idx_cart_items_cart_id ON cart_items(cart_id);

-- Create an index on customer_id for faster lookups
CREATE INDEX IF NOT EXISTS idx_carts_customer_id ON carts(customer_id);

-- Optional: Insert some sample data for testing
-- INSERT INTO carts (id, customer_id) VALUES (1, 'customer1')
--     ON CONFLICT DO NOTHING;
--
-- INSERT INTO cart_items (cart_id, quantity, product_id)
-- VALUES (1, 2, 'product1'), (1, 1, 'product2')
--     ON CONFLICT DO NOTHING;
--
-- -- Reset the sequence to start after our sample data
-- ALTER SEQUENCE carts_id_seq RESTART WITH 2;
-- ALTER SEQUENCE cart_items_id_seq RESTART WITH 3;