package org.drp09.backend.service.impl;

import org.drp09.backend.mapper.UserMapper;
import org.drp09.backend.pojo.User;
import org.drp09.backend.service.UserService;
import org.drp09.backend.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public User findByUsername(String username) {
    return userMapper.findByUsername(username);
  }

  @Override
  public void register(String username, String gender, String password, String trueName, String cardNumber, String cvv) {
    LocalDateTime now = LocalDateTime.now();
    userMapper.register(username, gender, password, now, trueName, cardNumber, cvv);
  }

  @Override
  public User userInfo() {
    Map<String, Object> claims = ThreadLocalUtil.get();
    String username = (String) claims.get("username");
    return findByUsername(username);
  }

  @Override
  public void update(User user) {
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer userId = (Integer) claims.get("id");
    user.setId(userId);
    LocalDateTime now = LocalDateTime.now();
    user.setUpdateTime(now);
    userMapper.update(user);
  }

  @Override
  public void resetPassword(String newPwd) {
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer userId = (Integer) claims.get("id");
    LocalDateTime now = LocalDateTime.now();
    userMapper.resetPassword(newPwd, userId, now);
  }
}
