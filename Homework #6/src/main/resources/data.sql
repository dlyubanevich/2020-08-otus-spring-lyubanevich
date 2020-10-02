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

INSERT INTO COMMENTS (ID, TEXT) values (1, 'Great!');
INSERT INTO COMMENTS (ID, TEXT) values (2, 'Not bad');
INSERT INTO COMMENTS (ID, TEXT) values (3, 'Best book ever!');

INSERT INTO books_authors (author_id, book_id) values (1, 1);
INSERT INTO books_authors (author_id, book_id) values (1, 2);
INSERT INTO books_authors (author_id, book_id) values (3, 3);
INSERT INTO books_authors (author_id, book_id) values (3, 4);
INSERT INTO books_authors (author_id, book_id) values (2, 5);
INSERT INTO books_authors (author_id, book_id) values (3, 6);

INSERT INTO books_genres (book_id, genre_id) values (1, 3);
INSERT INTO books_genres (book_id, genre_id) values (2, 3);
INSERT INTO books_genres (book_id, genre_id) values (3, 2);
INSERT INTO books_genres (book_id, genre_id) values (4, 2);
INSERT INTO books_genres (book_id, genre_id) values (5, 1);
INSERT INTO books_genres (book_id, genre_id) values (6, 2);

INSERT INTO books_comments (book_id, comment_id) values (1, 1);
INSERT INTO books_comments (book_id, comment_id) values (1, 2);
INSERT INTO books_comments (book_id, comment_id) values (2, 3);
