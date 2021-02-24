package com.commerce.app.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import com.commerce.app.domain.enumeration.OrderStatus;
import com.commerce.app.domain.enumeration.PaymentMethod;

/**
 * A DTO for the {@link com.commerce.app.domain.ShoppingCart} entity.
 */
@ApiModel(description = "Shopping cart to hold users orders")
public class ShoppingCartDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant placedDate;

    @NotNull
    private OrderStatus status;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal totalPrice;

    @NotNull
    private PaymentMethod paymentMethod;


    private Long customerDetailsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(Instant placedDate) {
        this.placedDate = placedDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getCustomerDetailsId() {
        return customerDetailsId;
    }

    public void setCustomerDetailsId(Long customerDetailsId) {
        this.customerDetailsId = customerDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingCartDTO)) {
            return false;
        }

        return id != null && id.equals(((ShoppingCartDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
            "id=" + getId() +
            ", placedDate='" + getPlacedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", customerDetailsId=" + getCustomerDetailsId() +
            "}";
    }
}
