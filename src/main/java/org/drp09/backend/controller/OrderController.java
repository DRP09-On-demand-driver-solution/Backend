package org.drp09.backend.controller;

import org.drp09.backend.pojo.Order;
import org.drp09.backend.pojo.Result;
import org.drp09.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public Result<Void> create(@RequestBody Order order) {
    orderService.create(order);
    return Result.success();
  }

  @GetMapping("/self")
  public Result<List<Order>> listSelf() {
    List<Order> orders = orderService.listSelf();
    return Result.success(orders);
  }

  @GetMapping("/others")
  public Result<List<Order>> listOthers() {
    List<Order> orders = orderService.listOthers();
    return Result.success(orders);
  }

  @GetMapping("/accept")
  public Result<List<Order>> listAccepted() {
    List<Order> orders = orderService.listAccepted();
    return Result.success(orders);
  }

  @PutMapping
  public Result<Void> update(@RequestBody Order order) {
    orderService.update(order);
    return Result.success();
  }

  @DeleteMapping
  public Result<Void> delete(Integer id) {
    orderService.delete(id);
    return Result.success();
  }

  @PatchMapping("/accept")
  public Result<Void> accept(@RequestBody Order order) {
    orderService.accept(order);
    return Result.success();
  }

  @PatchMapping("/finish")
  public Result<Void> finish(@RequestBody Order order) {
    orderService.finish(order);
    return Result.success();
  }

  @PatchMapping("/cancel")
  public Result<Void> cancel(@RequestBody Order order) {
    orderService.cancel(order);
    return Result.success();
  }

  @PatchMapping("/rate")
  public Result<Void> rate(@RequestBody Order order) {
    orderService.rate(order);
    return Result.success();
  }
}
