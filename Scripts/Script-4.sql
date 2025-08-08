-- 계정 생성
create user'user01'@'%' identified by 'user01';

-- db 생성
create database user01;

-- 권한 부여
grant all privileges on user01.* to 'user01'@'%';

-- 권한 저장
flush privileges;

-- 최종 저장
commit;

-- 테이블 대소문자 구분하는지 확인
show variables like 'lower_case_table_names';