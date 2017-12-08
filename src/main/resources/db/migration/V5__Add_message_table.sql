CREATE TABLE messages (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  sender BIGINT,
  to BIGINT NOT NULL,
  topic VARCHAR(255),
  body LONGTEXT,
  read BOOLEAN
);