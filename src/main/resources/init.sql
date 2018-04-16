DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS curriculums;
DROP TABLE IF EXISTS users_assignments;
DROP TABLE IF EXISTS users;


CREATE TABLE public.assignments (
    id serial NOT NULL,
    title text NOT NULL,
    question text NOT NULL,
    answer text,
    max_score integer,
    is_done boolean NOT NULL,
    is_published boolean NOT NULL
);


CREATE TABLE public.curriculums (
    id serial NOT NULL,
    title text NOT NULL,
    content text NOT NULL,
    is_published boolean NOT NULL
);


CREATE TABLE public.users (
    id serial NOT NULL,
    name text NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    role text NOT NULL
);



CREATE TABLE public.users_assignments (
    user_id integer NOT NULL,
    assignment_id integer NOT NULL
);


INSERT INTO users (name,email,password,role) values
	('Kiss Pista','a@a.hu','a','mentor'),
	('Nagy Pista','b@b.hu','a','student');

INSERT INTO curriculums (title,content,is_published) values
	('Example 1','example text','true'),
	('Example 2','example text2','true'),
	('Example 3','example text3','false');

INSERT INTO assignments (title,question,answer,max_score,is_done,is_published) values
	('Assignment 1','What?','',10,'false','true'),
	('Assignment 2','What?','',80,'false','true'),
	('Assignment 3','What?','',5,'false','false');
