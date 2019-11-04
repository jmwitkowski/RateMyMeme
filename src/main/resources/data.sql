insert into Role (id, role_name) values (nextval('role_seq'), 'USER');
insert into Role (id, role_name) values (nextval('role_seq'), 'ADMIN');

insert INTO USER (login,email,password,registration_date,role_id) values ('user', 'first@wp.pl', 'user', '2019-10-30', 1);

commit;
