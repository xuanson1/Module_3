use QuanLySinhVien;

select * from `Student` 
where Status = true;

select * from `Subject`
where Credit < 10;


 select S.StudentID, S.StudentName, C.ClassName
 from Student S join Class C on S.ClassID = C.ClassID;
 
 select S.StudentID, S.StudentName, C.ClassName
 from Student s join Class C on S.ClassID = C.ClassID
 where C.ClassName = 'A1';
 
 select S.StudentID, S.StudentName, Sub.SubName,M.Mark
 from Student S join Mark M on S.StudentID = M.StudentID join Subject Sub on M.SubID = Sub.SubID
 where Sub.SubName = 'CF';