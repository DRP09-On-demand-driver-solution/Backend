package org.drp09.backend.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
  private Integer id;
  private String username;
  private String gender;
  private String email;
  private String nickname;
  private String password;
  private String trueName;
  private String cardNumber;
  private String cvv;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
