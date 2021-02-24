package com.commerce.app.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.commerce.app.domain.ProductOrder} entity.
 */
@ApiModel(description = "Product order keeps track of orders")
public class ProductOrderDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Min(value = 0)
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal totalPrice;


    private Long productId;

    private String productName;

    private Long cartId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long shoppingCartId) {
        this.cartId = shoppingCartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductOrderDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductOrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductOrderDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", totalPrice=" + getTotalPrice() +
            ", productId=" + getProductId() +
            ", productName='" + getProductName() + "'" +
            ", cartId=" + getCartId() +
            "}";
    }
}
