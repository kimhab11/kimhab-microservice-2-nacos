CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        user_id INT not null,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--                         total_amount DECIMAL(10, 2) NOT NULL,
--                         status VARCHAR(50) NOT NULL
);

CREATE TABLE order_items (
                             id SERIAL PRIMARY KEY,
                             order_id INT REFERENCES orders(id) ON DELETE CASCADE,
                             book_id INT NOT NULL,
                             quantity INT NOT NULL,
                             total_price DECIMAL(100, 2) NOT NULL
);