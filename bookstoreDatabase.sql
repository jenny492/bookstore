DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS app_users;
DROP TABLE IF EXISTS categories;

CREATE TABLE app_users (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    user_role VARCHAR(50) NOT NULL
);

INSERT INTO app_users (username, password_hash, user_role)
VALUES
('user', '$2a$10$tAmc6.J5OZGgW89pWr8EFuQaguv1mnkKMMeawWxXbkL/whVkE.ioO', 'USER'),
('admin', '$2a$10$HAS4hoxt575J/95NCefPTuRgQl6WqEMlMM8waonVsXez3tk.pOwby', 'ADMIN');

CREATE TABLE categories (
    category_id BIGSERIAL PRIMARY KEY,
    cat_name VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO categories (cat_name)
VALUES
('Fiction'),
('Classic');

CREATE TABLE books (
    book_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(200),
	isbn VARCHAR(200),
	price INT,
    pub_year INT,
    category_id BIGINT REFERENCES categories(category_id)

);

INSERT INTO books (title, author, isbn, price, pub_year, category_id)
VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 9780743273565, 10.99, 1925, 1),
('To Kill a Mockingbird', 'Harper Lee', 9780061120084, 7.99, 1960, 2),
('1984', 'George Orwell', 9780451524935, 9.99, 1949, 2);

