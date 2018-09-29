DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `book_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  --KEY (`book_detail_id`)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `book_detail`;
CREATE TABLE `book_detail` (
  `id` int(11) NOT NULL,
  `number_of_pages` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- H2的语法和mysql不同，外键约束似乎有语法问题，因此只能单独拎出来
-- 这里book内嵌了book_detail_id外键约束到book_detail
ALTER TABLE book
ADD FOREIGN KEY (book_detail_id) 
REFERENCES book_detail(id);

CREATE TABLE company (
  id integer not null auto_increment,
  name varchar(255),
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;
    
CREATE TABLE employee (
  id integer not null auto_increment,
  name varchar(255),
  company_id integer,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

ALTER TABLE employee 
ADD FOREIGN KEY (company_id) 
REFERENCES company (id);