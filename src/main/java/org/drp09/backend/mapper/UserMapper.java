package org.drp09.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.drp09.backend.pojo.User;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM user WHERE username=#{username}")
  User findByUsername(String username);

  @Insert("INSERT INTO user(username, nickname, gender, password, create_time, update_time) " +
          "VALUES (#{username}, #{username}, #{gender}, #{password}, #{now}, #{now})")
  void register(String username, String gender, String password, LocalDateTime now);

  @Update("UPDATE user SET nickname=#{nickname}, email=#{email}, update_time=#{updateTime} WHERE id=#{id}")
  void update(User user);

  @Update("UPDATE user SET password=#{newPwd}, update_time=#{now} WHERE id=#{userId}")
  void resetPassword(String newPwd, Integer userId, LocalDateTime now);
}
