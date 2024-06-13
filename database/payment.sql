CREATE TABLE payments (
                          id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                          user_id BIGINT REFERENCES users(id),
                          course_id BIGINT REFERENCES courses(id),
                          amount DECIMAL(10, 2) NOT NULL,
                          status VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transactions (
                              id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                              payment_id BIGINT REFERENCES payments(id),
                              transaction_id VARCHAR(255) UNIQUE NOT NULL,
                              status VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Trigger function to update the updated_at column on row update
CREATE OR REPLACE FUNCTION update_timestamp()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger for the payments table
CREATE TRIGGER update_payments_timestamp
    BEFORE UPDATE ON payments
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
