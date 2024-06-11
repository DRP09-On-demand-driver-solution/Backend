package org.drp09.backend.service.impl;

import org.drp09.backend.mapper.OrderMapper;
import org.drp09.backend.pojo.Order;
import org.drp09.backend.service.OrderService;
import org.drp09.backend.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderMapper orderMapper;

  @Autowired
  public OrderServiceImpl(OrderMapper orderMapper) {
    this.orderMapper = orderMapper;
  }

  @Override
  public void create(Order order) {
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer id = (Integer) claims.get("id");
    LocalDateTime now = LocalDateTime.now();
    order.setCreator(id);
    order.setCreateTime(now);
    order.setUpdateTime(now);
    order.setStatus("Waiting");

    orderMapper.create(order);
  }

  @Override
  public List<Order> listSelf() {
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer userId = (Integer) claims.get("id");

    return orderMapper.listSelf(userId);
  }

  @Override
  public List<Order> listOthers() {
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer userId = (Integer) claims.get("id");

    return orderMapper.listOthers(userId);
  }

  @Override
  public void update(Order order) {
    orderMapper.update(order);
  }

  @Override
  public void delete(Integer id) {
    orderMapper.delete(id);
  }

  @Override
  public void accept(Order order) {
    order.setStatus("Accepted");
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer userId = (Integer) claims.get("id");
    order.setAcceptor(userId);
    LocalDateTime now = LocalDateTime.now();
    order.setUpdateTime(now);

    orderMapper.accept(order);
  }

  @Override
  public List<Order> listAccepted() {
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer userId = (Integer) claims.get("id");

    return orderMapper.listAccepted(userId);
  }

  @Override
  public void finish(Order order) {
    order.setStatus("Finished");
    LocalDateTime now = LocalDateTime.now();
    order.setUpdateTime(now);

    orderMapper.finish(order);
  }

  @Override
  public void cancel(Order order) {
    order.setStatus("Waiting");
    LocalDateTime now = LocalDateTime.now();
    order.setUpdateTime(now);

    orderMapper.cancel(order);
  }

  @Override
  public void rate(Order order) {
    order.setStatus("Rated");
    LocalDateTime now = LocalDateTime.now();
    order.setUpdateTime(now);

    orderMapper.rate(order);
  }
}
