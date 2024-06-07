package org.drp09.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
  private Integer status;
  private String message;
  private T data;

  public static <E> Result<E> success(E data) {
    return new Result<>(0, "Success", data);
  }

  public static Result<Void> success() {
    return new Result<>(0, "Success", null);
  }

  public static Result error(String message) {
    return new Result<>(1, message, null);
  }
}
