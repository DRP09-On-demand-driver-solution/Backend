package org.drp09.backend.service;

import org.drp09.backend.pojo.User;

public interface UserService {

  User findByUsername(String username);

  void register(String username, String gender, String password, String trueName, String cardNumber, String cvv);

  User userInfo();

  void update(User user);

  void resetPassword(String newPwd);
}
