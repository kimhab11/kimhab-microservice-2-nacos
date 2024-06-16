CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       published_date DATE,
                       price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

-- book_authors table (many-to-many relationship between books and authors)
CREATE TABLE book_authors (
                              book_id INT REFERENCES books(id) ON DELETE CASCADE,
                              author_id INT REFERENCES authors(id) ON DELETE CASCADE,
                              PRIMARY KEY (book_id, author_id)
);

CREATE TABLE book_categories (
                                 book_id INT REFERENCES books(id) ON DELETE CASCADE,
                                 category_id INT REFERENCES categories(id) ON DELETE CASCADE,
                                 PRIMARY KEY (book_id, category_id)
);
