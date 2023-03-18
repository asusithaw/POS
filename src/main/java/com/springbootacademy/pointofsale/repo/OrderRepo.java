package com.springbootacademy.pointofsale.repo;

import com.springbootacademy.pointofsale.dto.queryInterface.OrderDetailInterface;
import com.springbootacademy.pointofsale.entity.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query(value = "select c.customer_name as customerName, c.customer_address as customerAddress,"+
            "c.contact_numbers as contactNumbers, o.order_date as date, o.total as total from customer c, orders o "+
            " where c.active_state=?1 and c.customer_id = o.customer_id ",nativeQuery = true)
    List<OrderDetailInterface> getAllOrderDetails(boolean status, Pageable pageable);
}
