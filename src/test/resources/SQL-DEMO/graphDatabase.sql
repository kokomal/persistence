## DDL建表语句
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Person` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `personfriend`;
CREATE TABLE `personfriend` (
  `PersonID` bigint(20) DEFAULT NULL,
  `FriendID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


## data语句
INSERT INTO `person` VALUES ('1', 'Alice');
INSERT INTO `person` VALUES ('2', 'Bob');
INSERT INTO `person` VALUES ('99', 'Zach');

INSERT INTO `personfriend` VALUES ('1', '2');
INSERT INTO `personfriend` VALUES ('2', '1');
INSERT INTO `personfriend` VALUES ('2', '99');
INSERT INTO `personfriend` VALUES ('99', '1');

## data预览
## Person表
## ID  Person
## ----------
## 1	Alice
## 2	Bob
## 99	Zach

## PersonFriend表
## personID	FriendID
## -----------------
## 1		2
## 2		1
## 2		99
## 99		1

## bob的朋友是谁
select p1.Person from Person p1
inner JOIN personfriend on personfriend.FriendID = p1.ID
inner JOIN Person p2 on personfriend.PersonID = p2.ID
where p2.Person = 'Bob';

## 谁的朋友是Bob
select p1.Person from Person p1
inner JOIN personfriend on personfriend.PersonID = p1.ID
inner JOIN Person p2 on personfriend.FriendID = p2.ID
where p2.Person = 'Bob';

## Bob的朋友的朋友（比较复杂了）
select DISTINCT p1.Person as PERSON, p2.Person as FRIEND_OF_FRIEND
from personfriend pf1 
INNER JOIN Person p1 on pf1.PersonID = p1.ID
INNER JOIN personfriend pf2 on pf2.PersonID = pf1.FriendID
INNER JOIN Person p2 on pf2.FriendID = p2.ID
WHERE p1.Person = 'Alice' AND pf2.FriendID <> p1.ID;