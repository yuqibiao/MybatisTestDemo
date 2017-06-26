use yu;

CREATE TABLE user(
  id INT NOT NULL  AUTO_INCREMENT,
  username VARCHAR(10) NOT NULL ,
  sex CHAR(2) DEFAULT '0',
  birthday DATE,
  address VARCHAR(100),
  PRIMARY KEY (id)
);

INSERT  INTO user(username, sex, birthday, address) VALUES ('张三','1','1992-06-05','湖北武汉');
INSERT  INTO user(username, sex, birthday, address) VALUES ('李四','1','1992-06-05','湖北武汉');
INSERT  INTO user(username, sex, birthday, address) VALUES ('王五','1','1992-06-05','湖北武汉');
INSERT  INTO user(username, sex, birthday, address) VALUES ('张小华','1','1992-06-05','广东深圳');

SELECT * FROM user;


CREATE TABLE user_order(
  id INT AUTO_INCREMENT,
  user_id INT,
  createTime DATE ,
  tip VARCHAR (500),
  PRIMARY KEY (id)
);
