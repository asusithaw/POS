package com.springbootacademy.pointofsale.repo;

import com.springbootacademy.pointofsale.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDeatailsRepo extends JpaRepository<OrderDetails, Integer> {
}
