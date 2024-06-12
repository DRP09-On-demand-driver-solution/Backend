package org.drp09.backend.service;

import org.drp09.backend.pojo.Order;

import java.util.List;

public interface OrderService {
  void create(Order order);

  List<Order> listSelf();

  List<Order> listOthers();

  void update(Order order);

  void delete(Integer id);

  void accept(Order order);

  List<Order> listAccepted();

  void finish(Order order);

  void cancel(Order order);

  void rate(Order order);

  Order getById(Integer id);
}
