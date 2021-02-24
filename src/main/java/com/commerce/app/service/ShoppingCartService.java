package com.commerce.app.service;

import com.commerce.app.domain.ShoppingCart;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ShoppingCart}.
 */
public interface ShoppingCartService {

    /**
     * Save a shoppingCart.
     *
     * @param shoppingCart the entity to save.
     * @return the persisted entity.
     */
    ShoppingCart save(ShoppingCart shoppingCart);

    /**
     * Get all the shoppingCarts.
     *
     * @return the list of entities.
     */
    List<ShoppingCart> findAll();


    /**
     * Get the "id" shoppingCart.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShoppingCart> findOne(Long id);

    Optional<ShoppingCart> findActiveCartByUser(String user);

    ShoppingCart addProductForUser(Long id, String user);

    ShoppingCart removeProductOrderForUser(final Long id, final String user);
    /**
     * Delete the "id" shoppingCart.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    ShoppingCart closeCartForUser(final String user, final String paymentType);
}
