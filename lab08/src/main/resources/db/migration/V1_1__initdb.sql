create table hash_users
(
    id       INT NOT NULL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);
insert into hash_users(id, username, password)
values (1, 'elise', '$2a$10$eVd7gUyIfgYKYWW0/eUwoek9h1LqwMXDgzPK7liSY79CtzPCDjTNi');
