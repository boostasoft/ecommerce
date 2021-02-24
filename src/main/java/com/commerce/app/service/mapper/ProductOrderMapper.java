package com.commerce.app.service.mapper;


import com.commerce.app.domain.*;
import com.commerce.app.service.dto.ProductOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductOrder} and its DTO {@link ProductOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, ShoppingCartMapper.class})
public interface ProductOrderMapper extends EntityMapper<ProductOrderDTO, ProductOrder> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "cart.id", target = "cartId")
    ProductOrderDTO toDto(ProductOrder productOrder);

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "cartId", target = "cart")
    ProductOrder toEntity(ProductOrderDTO productOrderDTO);

    default ProductOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(id);
        return productOrder;
    }
}
