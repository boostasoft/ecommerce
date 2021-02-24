package com.commerce.app.service.impl;

import com.commerce.app.domain.CustomerDetails;
import com.commerce.app.domain.Product;
import com.commerce.app.domain.ProductOrder;
import com.commerce.app.domain.enumeration.OrderStatus;
import com.commerce.app.domain.enumeration.PaymentMethod;
import com.commerce.app.repository.CustomerDetailsRepository;
import com.commerce.app.repository.ProductOrderRepository;
import com.commerce.app.repository.ProductRepository;
import com.commerce.app.service.ProductOrderService;
import com.commerce.app.service.ProductService;
import com.commerce.app.service.ShoppingCartService;
import com.commerce.app.domain.ShoppingCart;
import com.commerce.app.repository.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ShoppingCart}.
 */
@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    private final ShoppingCartRepository shoppingCartRepository;

    private final CustomerDetailsRepository customerDetailsRepository;

    private final ProductService productService;

    private final ProductOrderRepository productOrderRepository;

    private final ProductOrderService productOrderService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, CustomerDetailsRepository customerDetailsRepository, ProductService productService, ProductOrderRepository productOrderRepository, ProductOrderService productOrderService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerDetailsRepository = customerDetailsRepository;
        this.productService = productService;
        this.productOrderRepository = productOrderRepository;
        this.productOrderService = productOrderService;
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        log.debug("Request to save ShoppingCart : {}", shoppingCart);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShoppingCart> findAll() {
        log.debug("Request to get all ShoppingCarts");
        return shoppingCartRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ShoppingCart> findOne(Long id) {
        log.debug("Request to get ShoppingCart : {}", id);
        return shoppingCartRepository.findById(id);
    }

    @Override
    public Optional<ShoppingCart> findActiveCartByUser(String user) {
        Optional<ShoppingCart> oCart = shoppingCartRepository.findFirstByCustomerDetailsUserLoginAndStatusOrderByIdAsc(user, OrderStatus.PENDING);
        // also serves as lazy init of orders
        oCart.ifPresent(shoppingCart -> {
            log.info("Cart for user {} has {} orders", user, shoppingCart.getOrders().size());
        });
        return oCart;
    }

    @Override
    public ShoppingCart addProductForUser(Long id, String user) throws EntityNotFoundException {

        Optional<ShoppingCart> carts = findActiveCartByUser(user);
        ShoppingCart activeCart = carts.orElseGet(() -> {
            Optional<CustomerDetails> customer = customerDetailsRepository.findOneByUserLogin(user);
            ShoppingCart shoppingCart = new ShoppingCart(Instant.now(), OrderStatus.PENDING, BigDecimal.ZERO, PaymentMethod.CREDIT_CARD, customer.get());
            return shoppingCartRepository.save(shoppingCart);
        });

        Product product = productService.findOne(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ProductOrder order;
        List<ProductOrder> orders = activeCart.getOrders().stream().filter(productOrder -> productOrder.getProduct().getId().equals(id)).collect(Collectors.toList());
        if (orders.isEmpty()) {
            order = new ProductOrder();
            order.setQuantity(1);
            order.setTotalPrice(product.getPrice());
            order.setProduct(product);
            order.setCart(activeCart);
            activeCart.addOrder(order);
        } else {
            order = orders.get(0);
            order.setQuantity(order.getQuantity() + 1);
            order.setTotalPrice(product.getPrice().multiply(new BigDecimal(order.getQuantity())));
        }
        productOrderRepository.save(order);
        return shoppingCartRepository.save(activeCart);
    }

    @Override
    public ShoppingCart removeProductOrderForUser(final Long id, final String user) {
        Optional<ShoppingCart> carts = findActiveCartByUser(user);
        ShoppingCart activeCart = carts.orElseThrow(() -> new EntityNotFoundException("Shopping cart not found"));
        List<ProductOrder> orders = activeCart.getOrders().stream().filter(productOrder -> productOrder.getId().equals(id)).collect(Collectors.toList());
        if (orders.isEmpty()) {
            throw new EntityNotFoundException("Product order not found in cart");
        } else {
            ProductOrder order = orders.get(0);
            activeCart.removeOrder(order);
            productOrderService.delete(order.getId());
        }
        return shoppingCartRepository.save(activeCart);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShoppingCart : {}", id);
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public ShoppingCart closeCartForUser(final String user, final String paymentType) {
        Optional<ShoppingCart> activeCart = findActiveCartByUser(user);
        ShoppingCart shoppingCart = activeCart.orElseThrow(() -> new EntityNotFoundException("Active shopping cart not found!"));
        shoppingCart.setStatus(OrderStatus.PAID);
        if (paymentType.equals("ideal")) {
            shoppingCart.setPaymentMethod(PaymentMethod.IDEAL);
        } else {
            shoppingCart.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        }
        return shoppingCartRepository.save(shoppingCart);
    }
}
