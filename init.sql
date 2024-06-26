CREATE DATABASE IF NOT EXISTS on_demand_driver_solution;

USE on_demand_driver_solution;

CREATE TABLE IF NOT EXISTS user(
                                   id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
                                   username VARCHAR(20) NOT NULL UNIQUE COMMENT 'username',
                                   gender VARCHAR(8) NOT NULL COMMENT 'gender',
                                   email VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'email address',
                                   nickname VARCHAR(20) NOT NULL DEFAULT '' COMMENT 'nickname',
                                   password VARCHAR(30) NOT NULL COMMENT 'password',
                                   create_time DATETIME NOT NULL COMMENT 'create time',
                                   update_time DATETIME NOT NULL COMMENT 'update time'
) COMMENT 'user table';

CREATE TABLE IF NOT EXISTS `order`(
                                      id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
                                      creator INT UNSIGNED NOT NULL COMMENT 'creator',
                                      acceptor INT UNSIGNED COMMENT 'acceptor',
                                      `from` VARCHAR(128) NOT NULL COMMENT 'customer location',
                                      `to` VARCHAR(128) NOT NULL COMMENT 'destination',
                                      gender_preference VARCHAR(20) NOT NULL COMMENT 'gender preference',
                                      transportation VARCHAR(32) NOT NULL COMMENT 'transportation',
                                      status VARCHAR(16) NOT NULL COMMENT 'waiting/accepted',
                                      comment VARCHAR(256) COMMENT 'comment',
                                      create_time DATETIME NOT NULL COMMENT 'create time',
                                      update_time DATETIME NOT NULL COMMENT 'update time',
                                      CONSTRAINT fk_creator_user FOREIGN KEY (creator) REFERENCES user(id) ON DELETE CASCADE,
                                      CONSTRAINT fk_acceptor_user FOREIGN KEY (acceptor) REFERENCES user(id) ON DELETE CASCADE
) COMMENT 'order table';