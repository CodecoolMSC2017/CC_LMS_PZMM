DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS curriculums;
DROP TABLE IF EXISTS users_assignments;
DROP TABLE IF EXISTS users;


CREATE TABLE public.assignments (
    id serial PRIMARY KEY,
    title text NOT NULL,
    question text NOT NULL,
    max_score integer,
    is_published boolean NOT NULL
);


CREATE TABLE public.curriculums (
    id serial PRIMARY KEY,
    title text NOT NULL,
    content text NOT NULL,
    is_published boolean NOT NULL
);


CREATE TABLE public.users (
    id serial PRIMARY KEY,
    name text NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    role text NOT NULL
);



CREATE TABLE public.users_assignments (
    user_id integer,
    assignment_id integer,
    answer text NOT NULL,
    PRIMARY KEY (user_id, assignment_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (assignment_id) REFERENCES assignments(id)
);


INSERT INTO users (name,email,password,role) values
	('Kiss Pista','a@a.hu','a','mentor'),
	('Nagy Pista','b@b.hu','a','student');

INSERT INTO curriculums (title,content,is_published) values
	('Example 1','example text','true'),
	('Example 2','example text2','true'),
	('Example 3','example text3','false');

INSERT INTO assignments (title,question,max_score,is_published) values
	('Assignment 1','What?',10,'true'),
	('Assignment 2','What?',80,'true'),
	('Assignment 3','What?',5,'false');

INSERT INTO users_assignments (user_id, assignment_id, answer) values
    (1, 1, 'I do not know.'),
    (1, 2, 'Blabla...'),
    (2, 1, 'Blaaaaaablaaaa...')
