DROP TABLE IF EXISTS teams CASCADE;
DROP TABLE IF EXISTS leagues CASCADE;
DROP TABLE IF EXISTS seasons CASCADE;
DROP TABLE IF EXISTS summaries CASCADE;

CREATE TABLE teams(id bigint generated by default as identity, name varchar(255), primary key (id));
CREATE TABLE leagues(id bigint generated by default as identity, name varchar(255), primary key (id));
CREATE TABLE seasons(id bigint generated by default as identity, name varchar(255), primary key (id));
CREATE TABLE summaries(id bigint generated by default as identity, team_id bigint, season_id bigint,
    league_id bigint, position int, games int, points int, primary key (id));

ALTER TABLE summaries ADD FOREIGN KEY (team_id) REFERENCES teams(id);
ALTER TABLE summaries ADD FOREIGN KEY (league_id) REFERENCES leagues(id);
ALTER TABLE summaries ADD FOREIGN KEY (season_id) REFERENCES seasons(id);
