INSERT INTO AUTHORS (ID, FIRST_NAME, LAST_NAME) values (1, 'Robert', 'Martin');
INSERT INTO AUTHORS (ID, FIRST_NAME, LAST_NAME) values (2, 'Lev', 'Tolstoy');
INSERT INTO AUTHORS (ID, FIRST_NAME, LAST_NAME) values (3, 'Teodor', 'Dreiser');

INSERT INTO GENRES (ID, NAME) values (1, 'Historical novel');
INSERT INTO GENRES (ID, NAME) values (2, 'Novel');
INSERT INTO GENRES (ID, NAME) values (3, 'Computer science');

INSERT INTO BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (1, 'Clean code', 1, 3);
INSERT INTO BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (2, 'The clean coder', 1, 3);
INSERT INTO BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (3, 'The Financier', 3, 2);
INSERT INTO BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (4, 'The Titan', 3, 2);
INSERT INTO BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (5, 'War and peace', 2, 1);
INSERT INTO BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (6, 'The Genius', 3, 2);