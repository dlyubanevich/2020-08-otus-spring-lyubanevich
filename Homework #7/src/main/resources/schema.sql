DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS genres CASCADE;
DROP TABLE IF EXISTS books CASCADE;

CREATE TABLE authors(id bigint generated by default as identity, first_name varchar(255), last_name varchar(255), primary key (id));
CREATE TABLE genres(id bigint generated by default as identity, name varchar(255), primary key (id));
CREATE TABLE books(id bigint generated by default as identity, name varchar(255), primary key (id));
CREATE TABLE books_comments(id bigint generated by default as identity, text varchar(255), book_id bigint, primary key (id));

CREATE TABLE books_authors(book_id bigint not null, author_id bigint not null);
CREATE TABLE books_genres(book_id bigint not null, genre_id bigint not null);

ALTER TABLE books_authors ADD FOREIGN KEY (author_id) REFERENCES authors(id);
ALTER TABLE books_authors ADD FOREIGN KEY (book_id) REFERENCES books(id);
ALTER TABLE books_genres ADD FOREIGN KEY (genre_id) REFERENCES genres(id);
ALTER TABLE books_genres ADD FOREIGN KEY (book_id) REFERENCES books(id);
ALTER TABLE books_comments ADD FOREIGN KEY (book_id) REFERENCES books(id);

