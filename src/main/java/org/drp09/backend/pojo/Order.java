package org.drp09.backend.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
  private Integer id;
  private Integer creator;
  private String creatorNickname;
  private Integer acceptor;
  private String acceptorNickname;
  private String from;
  private String to;
  private String genderPreference;
  private String transportation;
  private String status;
  private String comment;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private Double rating;
}
