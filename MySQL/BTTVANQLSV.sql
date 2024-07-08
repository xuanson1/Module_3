SELECT * FROM quanlysinhvien.student
where StudentName like 'h%';

select * from quanlysinhvien.class
where month(StartDate) = 12;

select * from quanlysinhvien.subject
where Credit between 3 and 5;

update student set ClassID = 2 where StudentName = 'Hung' ;

SELECT s.StudentName, su.SubName, m.Mark
FROM mark m
JOIN student s ON m.StudentID = s.StudentID
JOIN subject su ON m.SubID = su.SubID
ORDER BY m.Mark DESC, s.StudentName ASC
LIMIT 0, 1000;


