DROP DATABASE IF EXISTS kea;
CREATE DATABASE kea;
USE kea;


CREATE TABLE students(
	student_id INT AUTO_INCREMENT primary key,
    student_cpr varchar(10),
    student_name VARCHAR(50) NOT NULL,
    student_age int NOT NULL
    
);


CREATE TABLE teachers(
	teacher_id INT AUTO_INCREMENT PRIMARY KEY,
	teacher_name VARCHAR(50),
    teacher_mail VARCHAR (50),
    teacher_pw VARCHAR (50)
);


CREATE TABLE courses(
	course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_nameL VARCHAR(50),
    course_nameS VARCHAR(50),
    course_semester int
);

CREATE TABLE exsams (
    exsam_id INT AUTO_INCREMENT,
    exsam_name VARCHAR(25),
    PRIMARY KEY (exsam_id)
);

CREATE TABLE studentteacherexsam (
	fk_exsam_id INT,
    fk_student_id INT,
    fk_teacher_id INT,
    fk_course_id INT,
    exsam_grade INT,
    PRIMARY KEY (fk_exsam_id,fk_student_id,fk_teacher_id),
		FOREIGN KEY(fk_exsam_id)
			REFERENCES exsams (exsam_id),
		FOREIGN KEY (fk_student_id)
			REFERENCES students (student_id),
		FOREIGN KEY (fk_teacher_id)
			REFERENCES teachers (teacher_id),
		FOREIGN KEY (fk_course_id)
			REFERENCES courses (course_id)
);

CREATE TABLE studentcourses (
    fk_student_id INT,
    fk_course_id INT,
    PRIMARY KEY (fk_student_id , fk_course_id),
    FOREIGN KEY (fk_student_id)
        REFERENCES students (student_id),
    FOREIGN KEY (fk_course_id)
        REFERENCES courses (course_id)
);

CREATE TABLE assignments(
	assignment_id INT AUTO_INCREMENT,
    assignment_name VARCHAR(50),
    fk_course_id INT,
    PRIMARY KEY(assignment_id, fk_course_id),
    FOREIGN KEY(fk_course_id) REFERENCES courses(course_id)
);

ALTER TABLE students ADD UNIQUE INDEX cpr (student_cpr);
ALTER TABLE teachers ADD UNIQUE INDEX mail (teacher_mail);
ALTER TABLE courses ADD UNIQUE INDEX course (course_nameL, course_nameS);
