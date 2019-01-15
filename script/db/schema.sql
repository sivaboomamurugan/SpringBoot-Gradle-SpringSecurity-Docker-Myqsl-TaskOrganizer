# MySQL Setup
SET default_storage_engine=InnoDB;
USE task_organizer;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS user_task_mapping;

CREATE TABLE user (
`userid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`username` VARCHAR(25) NOT NULL,
`password` VARCHAR(50) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE task (
`taskid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`taskname` VARCHAR(50) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE user_task_mapping (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, user_id INT, task_id INT, completed BOOLEAN,
   FOREIGN KEY (user_id) REFERENCES user(userid)
   ON DELETE CASCADE,
   FOREIGN KEY (task_id) REFERENCES task(taskid)
   ON DELETE CASCADE
) ENGINE=INNODB;