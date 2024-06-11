package org.drp09.backend.mapper;

import org.apache.ibatis.annotations.*;
import org.drp09.backend.pojo.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

  @Insert("INSERT INTO `order`(creator, `from`, `to`, gender_preference, transportation, status, comment, create_time, update_time) " +
          "VALUES (#{creator}, #{from}, #{to}, #{genderPreference}, #{transportation}, #{status}, #{comment}, #{createTime}, #{updateTime})")
  void create(Order order);

  @Select("SELECT *, (SELECT nickname FROM user WHERE `order`.creator=user.id) AS creator_nickname, " +
          "(SELECT nickname FROM user WHERE `order`.acceptor=user.id) AS acceptor_nickname " +
          "FROM `order` WHERE `order`.creator=#{userId}")
  List<Order> listSelf(Integer userId);

  @Select("SELECT *, (SELECT nickname FROM user WHERE `order`.creator=user.id) AS creator_nickname, " +
          "(SELECT nickname FROM user WHERE `order`.acceptor=user.id) AS acceptor_nickname " +
          "FROM `order` WHERE creator!=#{userId} AND status='Waiting' AND (gender_preference='No_preference' OR gender_preference=(SELECT gender FROM user WHERE id=#{userId}))")
  List<Order> listOthers(Integer userId);

  @Update("UPDATE `order` SET `from`=#{from}, `to`=#{to}, gender_preference=#{genderPreference}, transportation=#{transportation}, comment=#{comment} " +
          "WHERE id=#{id}")
  void update(Order order);

  @Delete("DELETE FROM `order` WHERE id=#{id}")
  void delete(Integer id);

  @Update("UPDATE `order` SET acceptor=#{acceptor}, status=#{status}, update_time=#{updateTime} WHERE id=#{id}")
  void accept(Order order);

  @Select("SELECT *, (SELECT nickname FROM user WHERE `order`.creator=user.id) AS creator_nickname, " +
          "(SELECT nickname FROM user WHERE `order`.acceptor=user.id) AS acceptor_nickname " +
          "FROM `order` WHERE acceptor=#{userId}")
  List<Order> listAccepted(Integer userId);

  @Update("UPDATE `order` SET status=#{status}, update_time=#{updateTime} WHERE id=#{id}")
  void finish(Order order);

  @Update("UPDATE `order` SET status=#{status}, acceptor=#{acceptor}, update_time=#{updateTime} WHERE id=#{id}")
  void cancel(Order order);

  @Update("UPDATE `order` SET status=#{status}, rating=#{rating}, update_time=#{updateTime} WHERE id=#{id}")
  void rate(Order order);
}
