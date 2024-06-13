package org.drp09.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.drp09.backend.pojo.User;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {

  @Select("SELECT id FROM user WHERE username=#{username}")
  Integer getId(String username);

  @Insert("INSERT INTO user(username, nickname, gender, password, phone, true_name, card_number, cvv, create_time, update_time) " +
          "VALUES (#{username}, #{username}, #{gender}, #{password}, #{phone}, #{trueName}, #{cardNumber}, #{cvv}, #{now}, #{now})")
  void register(String username, String gender, String password, String phone, LocalDateTime now, String trueName, String cardNumber, String cvv);

  @Update("UPDATE user SET nickname=#{nickname}, email=#{email}, phone=#{phone}, update_time=#{updateTime} WHERE id=#{id}")
  void update(User user);

  @Update("UPDATE user SET password=#{newPwd}, update_time=#{now} WHERE id=#{userId}")
  void resetPassword(String newPwd, Integer userId, LocalDateTime now);

  @Select("SELECT *, " +
          "(SELECT COUNT(*) FROM `order` WHERE acceptor=#{id} AND status IN ('Finished', 'Rated')) AS order_num, " +
          "(SELECT COALESCE(AVG(rating), 5.0) FROM `order` WHERE acceptor=#{id} AND status IN ('Finished', 'Rated')) AS rating " +
          "FROM user WHERE id=#{id}")
  User userInfo(Integer id);
}
