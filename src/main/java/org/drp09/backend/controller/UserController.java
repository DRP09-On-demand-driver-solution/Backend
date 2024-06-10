package org.drp09.backend.controller;

import org.drp09.backend.pojo.Result;
import org.drp09.backend.pojo.User;
import org.drp09.backend.service.UserService;
import org.drp09.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public Result<Void> register(String username, String gender, String password, String rePassword, String trueName, String cardNumber, String cvv) {
    User user = userService.findByUsername(username);

    if (user == null) {
      if (password.equals(rePassword)) {
        userService.register(username, gender, password, trueName, cardNumber, cvv);
        return Result.success();
      } else {
        return Result.error("Passwords do not match");
      }
    } else {
      return Result.error("Username already registered");
    }
  }

  @PostMapping("/login")
  public Result<String> login(String username, String password) {
    User user = userService.findByUsername(username);
    if (user == null) {
      return Result.error("Wrong username");
    }

    if (password.equals(user.getPassword())) {
      Map<String, Object> claims = new HashMap<>();
      claims.put("id", user.getId());
      claims.put("username", user.getUsername());
      String token = JwtUtil.genToken(claims);

      return Result.success(token);
    }

    return Result.error("Wrong password");
  }

  @GetMapping("/userInfo")
  public Result<User> userInfo() {
    User user = userService.userInfo();
    return Result.success(user);
  }

  @PutMapping("/update")
  public Result<Void> update(@RequestBody User user) {
    userService.update(user);
    return Result.success();
  }

  @PatchMapping("/resetPassword")
  public Result<Void> resetPassword(@RequestBody Map<String, String> params) {
    String newPwd = params.get("password");
    String rePwd = params.get("rePassword");
    if (newPwd.equals(rePwd)) {
      userService.resetPassword(newPwd);
      return Result.success();
    } else {
      return Result.error("Passwords do not match");
    }
  }
}
