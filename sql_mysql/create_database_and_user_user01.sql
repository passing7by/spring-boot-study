create database user01;
show databases;

-- user01 아이디를 가진 유저가 localhost로만 접속 가능(외부에서 접속 불가)
-- create user 'user01'@'localhost' identified by 'user01'; 
-- grant all privileges on user01.* to 'user01'@'localhost';

-- %: 어떤 아이피로든 접속 가능
create user 'user01'@'%' identified by 'user01'; 
grant all privileges on user01.* to 'user01'@'%';

-- 권한을 save하겠다는 뜻
flush privileges;