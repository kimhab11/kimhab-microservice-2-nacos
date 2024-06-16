CREATE TABLE reviews (
                         review_id SERIAL PRIMARY KEY,
                         book_id INT NOT NULL,
                         user_id INT NOT NULL,
                         rating INT CHECK (rating >= 1 AND rating <= 5),
                         review_text TEXT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);