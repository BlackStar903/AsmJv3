create database AsmJV3
use AsmJV3
if object_id('STUDENTS') is not null
drop table STUDENTS 
create table STUDENTS
(
	MASV nvarchar(50) not null,
	Hoten nvarchar(50) null,
	Email nvarchar(50) null,
	SoDT nvarchar(15) null,
	Gioitinh bit null,
	Diachi nvarchar(50) null,
	Hinh nvarchar(MAX) null,
	constraint pk_STUDENTS primary key(MASV)
)

if object_id('GRADE') is not null
drop table GRADE 
create table GRADE
(
	ID INT identity NOT NULL,
	MASV NVARCHAR(50) NULL,
	Tienganh int null,
	Tinhoc int null,
	GDTC INT NULL,
	CONSTRAINT FK_GRADE_STUDENTS FOREIGN KEY(MASV)
						REFERENCES STUDENTS,
	CONSTRAINT PK_GRADE PRIMARY KEY(ID)
)

if object_id('USERS') is not null
drop table USERS 
create table USERS
(
	username nvarchar(50) not null,
	password nvarchar(50) null,
	role nvarchar(50) null,
	constraint pk_USERS primary key(username)
)

--users
select * from users
delete users
insert into users values
	('hoan','123',N'teacher')
insert into users values
	('dep','123',N'manager')
insert into users values
	('trai','123',N'teacher')

--student
select * from students
delete students
insert into students values
	('PH45768',N'Trần Phúc Hoàn','hoantpph45768@fpt.edu.vn','0978289999','true'
		,N'Hà Nội','VietnamFlag')
insert into students values
	('PH45555',N'Trần Trung Quân','quanttph45555@fpt.edu.vn','0978288888','true'
		,N'Hà Nam','VietnamFlag')
insert into students values
	('PH45568',N'Trần Thị Hay','hayttph45567@fpt.edu.vn','0978286666','false'
		,N'Thái Bình','VietnamFlag')

--GRADE
select * from grade
delete from grade
insert into grade values 
	('PH45768','10','10','10')
insert into grade values 
	('PH45555','9','9','9')
insert into grade values 
	('PH45568','10','8','10')

 insert into grade values ('PH99999','9','9','9')

select s.MASV,s.Hoten,g.Tinhoc,g.Tienganh,g.GDTC
		,(g.Tinhoc+g.Tienganh+g.GDTC)/3 [Điểm trung bình]
from grade g join STUDENTS s on g.MASV = s.MASV



select * from USERS 
where username = 'hoan' and password ='123'
		and role ='teacher'

delete from grade where id = 8
delete from STUDENTS where hoten = N'Trần Thị Hay'
select * from students
select * from grade
update Grade set Tienganh= 5,tinhoc= 6, gdtc = 7 where masv = 'PH45555'
select top 3 * from grade 
order by  (tienganh+tinhoc+gdtc)/3 desc