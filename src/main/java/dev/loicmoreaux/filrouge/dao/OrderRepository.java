package dev.loicmoreaux.filrouge.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.loicmoreaux.filrouge.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
