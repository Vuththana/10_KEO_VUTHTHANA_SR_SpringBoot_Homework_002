CREATE TABLE students(
    student_id SERIAL PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE instructors(
  instructor_id SERIAL PRIMARY KEY,
  instructor_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL
);

CREATE TABLE courses(
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    description TEXT,
    instructor_id INTEGER,
    CONSTRAINT fk_instructor FOREIGN KEY (instructor_id) REFERENCES instructors(instructor_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE student_course(
    student_id INTEGER,
    course_id INTEGER,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (course_id) REFERENCES  courses(course_id) ON DELETE CASCADE ON UPDATE CASCADE
);