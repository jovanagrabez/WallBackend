insert into user (id, password, username) values (1, 'jovana123','jovana123');

insert into post(id,average,numberr ,text,date_and_time,author_id) values  (1,0,0,'Ovaj post je o tome i tome bla bla ','2017-11-17 15:30:14',1);
insert into post(id, average,numberr,text,date_and_time,author_id) values  (2,0,0,'Ovaj post je o tome i tome bla bla ','2017-11-22 15:30:14',1);
insert into post(id, average,numberr,text,date_and_time,author_id) values  (3,0,0,'Ovaj post je o tome i tome bla bla ','2017-11-14 15:30:14',1);

insert into comment(id,date_and_time, author_id,text) values (1,'2017-11-17 15:30:14',1,'komentar 1');
insert into comment(id,date_and_time, author_id,text) values (2,'2017-11-17 15:30:14',1,'komentar 2');


insert into post_comments(post_id, comments_id) values (1,1);
insert into post_comments(post_id, comments_id) values (1,2);

