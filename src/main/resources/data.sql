insert into Role (id, role_name) values (nextval('role_seq'), 'USER');
insert into Role (id, role_name) values (nextval('role_seq'), 'ADMIN');

insert INTO USER (login,email,password,registration_date,role_id) values ('user', 'first@wp.pl', '$2a$10$KvxPudrieuxpEgxw3e4yPOuYK59PgfQshx3RaVUTCpbKB82DC/0RC', '2019-10-30', 1);
insert INTO USER (login,email,password,registration_date,role_id) values ('user2', 'second@wp.pl', '$2a$10$KvxPudrieuxpEgxw3e4yPOuYK59PgfQshx3RaVUTCpbKB82DC/0RC', '2019-10-30', 1);
insert INTO USER (login,email,password,registration_date,role_id) values ('admin', 'admin@gmail.com', '$2a$10$KCPhEhCDW3uJuKDAc8fApOkv4QBPnEqZ6RXCpw2vywB9JCAow7utC', '2019-11-13', 2);

INSERT INTO Meme (ID,DATE_UPLOAD,NAME_MEME,RECEIVED_MINUSES,RECEIVED_PLUSES,SOURCE_ADRESS,USER_LOGIN) values (nextval('meme_seq'),'2019-11-01 19:01','meme1',0,0,'memebase/kiedys-to-byli-programisci.jpg','user');
INSERT INTO Meme (ID,DATE_UPLOAD,NAME_MEME,RECEIVED_MINUSES,RECEIVED_PLUSES,SOURCE_ADRESS,USER_LOGIN) values (nextval('meme_seq'),'2019-11-02 19:22','meme2',0,0,'memebase/HehehelloMEME.jpg','user');
INSERT INTO Meme (ID,DATE_UPLOAD,NAME_MEME,RECEIVED_MINUSES,RECEIVED_PLUSES,SOURCE_ADRESS,USER_LOGIN) values (nextval('meme_seq'),'2019-11-01 23:45','meme3',0,0,'memebase/66QC917gOTDokD7R.jpg','user2');


commit;
