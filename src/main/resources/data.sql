insert into Role (id, role_name) values (nextval('role_seq'), 'USER');
insert into Role (id, role_name) values (nextval('role_seq'), 'ADMIN');

insert INTO USER (login,email,password,registration_date,role_id) values ('user', 'first@wp.pl', 'user', '2019-10-30', 1);
insert INTO USER (login,email,password,registration_date,role_id) values ('user2', 'second@wp.pl', 'user2', '2019-10-30', 1);

INSERT INTRO Meme (ID,DATE_UPLOAD,NAME_MEME,RECEIVED_MINUSES,RECEIVED_PLUSES,SOURCE_ADRESS,USER_LOGIN) value (nextval('meme_seq'),'2019-11-04','meme1',0,0,'memebase/kiedys-to-byli-programisci.jpg','user');
INSERT INTRO Meme (ID,DATE_UPLOAD,NAME_MEME,RECEIVED_MINUSES,RECEIVED_PLUSES,SOURCE_ADRESS,USER_LOGIN) value (nextval('meme_seq'),'2019-11-02','meme2',0,0,'memebase/HehehelloMEME.jpg','user');
INSERT INTRO Meme (ID,DATE_UPLOAD,NAME_MEME,RECEIVED_MINUSES,RECEIVED_PLUSES,SOURCE_ADRESS,USER_LOGIN) value (nextval('meme_seq'),'2019-11-01','meme3',0,0,'memebase/66QC917gOTDokD7R.jpg','user2');


commit;
