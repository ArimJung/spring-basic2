CREATE TABLE BOARD(
	BID INT PRIMARY KEY,
	TITLE VARCHAR(20) NOT NULL,
	WRITER VARCHAR(20) NOT NULL,
	CONTENT VARCHAR(100) NOT NULL,
	CNT INT DEFAULT 0,
	REGDATE DATE DEFAULT SYSDATE
);


select * from BOARD;

INSERT INTO BOARD(BID,TITLE,WRITER,CONTENT) VALUES(1,'力格','包府磊','郴侩');

drop table reply_Re;
drop table llike;
drop table reply;
drop table board;
drop table novel;
drop table member;