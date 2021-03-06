INSERT INTO AUTHORS (ID, FIRST_NAME, LAST_NAME) values (1, 'Robert', 'Martin');
INSERT INTO AUTHORS (ID, FIRST_NAME, LAST_NAME) values (2, 'Lev', 'Tolstoy');
INSERT INTO AUTHORS (ID, FIRST_NAME, LAST_NAME) values (3, 'Teodor', 'Dreiser');

INSERT INTO GENRES (ID, NAME) values (1, 'Historical novel');
INSERT INTO GENRES (ID, NAME) values (2, 'Novel');
INSERT INTO GENRES (ID, NAME) values (3, 'Computer science');

INSERT INTO BOOKS (ID, NAME) values (1, 'Clean code');
INSERT INTO BOOKS (ID, NAME) values (2, 'The clean coder');
INSERT INTO BOOKS (ID, NAME) values (3, 'The Financier');
INSERT INTO BOOKS (ID, NAME) values (4, 'The Titan');
INSERT INTO BOOKS (ID, NAME) values (5, 'War and peace');
INSERT INTO BOOKS (ID, NAME) values (6, 'The Genius');

INSERT INTO books_comments (ID, TEXT, BOOK_ID) values (1, 'Great!', 1);
INSERT INTO books_comments (ID, TEXT, BOOK_ID) values (2, 'Not bad', 1);
INSERT INTO books_comments (ID, TEXT, BOOK_ID) values (3, 'Best book ever!', 2);

INSERT INTO books_authors (book_id, author_id) values (1, 1);
INSERT INTO books_authors (book_id, author_id) values (2, 1);
INSERT INTO books_authors (book_id, author_id) values (3, 3);
INSERT INTO books_authors (book_id, author_id) values (4, 3);
INSERT INTO books_authors (book_id, author_id) values (5, 2);
INSERT INTO books_authors (book_id, author_id) values (6, 3);

INSERT INTO books_genres (book_id, genre_id) values (1, 3);
INSERT INTO books_genres (book_id, genre_id) values (2, 3);
INSERT INTO books_genres (book_id, genre_id) values (3, 2);
INSERT INTO books_genres (book_id, genre_id) values (4, 2);
INSERT INTO books_genres (book_id, genre_id) values (5, 1);
INSERT INTO books_genres (book_id, genre_id) values (6, 2);

