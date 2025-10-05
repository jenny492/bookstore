DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS appUser;
DROP TABLE IF EXISTS category;

CREATE TABLE appUser (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    passwordHash VARCHAR(255) NOT NULL,
    userRole VARCHAR(50) NOT NULL
);

INSERT INTO appUser (username, passwordHash, userRole)
VALUES
('user', '$2a$10$tAmc6.J5OZGgW89pWr8EFuQaguv1mnkKMMeawWxXbkL/whVkE.ioO', 'USER'),
('admin', '$2a$10$HAS4hoxt575J/95NCefPTuRgQl6WqEMlMM8waonVsXez3tk.pOwby', 'ADMIN');

CREATE TABLE category (
    category_id BIGSERIAL PRIMARY KEY,
    cname VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO category (cname)
VALUES
('Fiction'),
('Classic');

CREATE TABLE book (
    book_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(200),
	isbn BIGINT,
	price INT,
    pubYear INT,
    category_id BIGINT,

    -- Viittaukset
    CONSTRAINT fk_book_category FOREIGN KEY (category_id)
        REFERENCES category(category_id)
        ON DELETE SET NULL
);

INSERT INTO book (title, author, isbn, price, pubYear, category_id)
VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 9780743273565, 10.99, 1925, 1),
('To Kill a Mockingbird', 'Harper Lee', 9780061120084, 7.99, 1960, 2),
('1984', 'George Orwell', 9780451524935, 9.99, 1949, 2);

