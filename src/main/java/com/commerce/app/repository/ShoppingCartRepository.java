package com.commerce.app.repository;

import com.commerce.app.domain.ShoppingCart;

import com.commerce.app.domain.enumeration.OrderStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the ShoppingCart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findFirstByCustomerDetailsUserLoginAndStatusOrderByIdAsc(String login, OrderStatus orderStatus);
}
