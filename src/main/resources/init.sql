# DROP TABLE IF EXISTS `order`;
# DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user(
                                   id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
                                   username VARCHAR(16) NOT NULL UNIQUE COMMENT 'username',
                                   gender VARCHAR(8) NOT NULL COMMENT 'gender',
                                   email VARCHAR(64) COMMENT 'email address',
                                   nickname VARCHAR(16) COMMENT 'nickname',
                                   password VARCHAR(16) NOT NULL COMMENT 'password',
                                   true_name VARCHAR(64) NOT NULL COMMENT 'real name',
                                   card_number VARCHAR(16) NOT NULL COMMENT 'card number',
                                   cvv VARCHAR(4) NOT NULL COMMENT 'cvv',
                                   create_time DATETIME NOT NULL COMMENT 'create time',
                                   update_time DATETIME NOT NULL COMMENT 'update time'
) COMMENT 'user table';

CREATE TABLE IF NOT EXISTS `order`(
                                      id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
                                      creator INT UNSIGNED NOT NULL COMMENT 'creator',
                                      acceptor INT UNSIGNED COMMENT 'acceptor',
                                      `from` VARCHAR(128) NOT NULL COMMENT 'customer location',
                                      `to` VARCHAR(128) NOT NULL COMMENT 'destination',
                                      gender_preference VARCHAR(16) NOT NULL COMMENT 'gender preference',
                                      transportation VARCHAR(32) NOT NULL COMMENT 'transportation',
                                      status VARCHAR(16) NOT NULL COMMENT 'waiting/accepted',
                                      comment VARCHAR(256) COMMENT 'comment',
                                      create_time DATETIME NOT NULL COMMENT 'create time',
                                      update_time DATETIME NOT NULL COMMENT 'update time',
                                      rating DOUBLE UNSIGNED DEFAULT 5.0 COMMENT 'customer rating',
                                      CONSTRAINT fk_creator_user FOREIGN KEY (creator) REFERENCES user(id) ON DELETE CASCADE,
                                      CONSTRAINT fk_acceptor_user FOREIGN KEY (acceptor) REFERENCES user(id) ON DELETE CASCADE
) COMMENT 'order table';