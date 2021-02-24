package com.commerce.app.service.mapper;


import com.commerce.app.domain.*;
import com.commerce.app.service.dto.ShoppingCartDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShoppingCart} and its DTO {@link ShoppingCartDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerDetailsMapper.class})
public interface ShoppingCartMapper extends EntityMapper<ShoppingCartDTO, ShoppingCart> {

    @Mapping(source = "customerDetails.id", target = "customerDetailsId")
    ShoppingCartDTO toDto(ShoppingCart shoppingCart);

    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "removeOrder", ignore = true)
    @Mapping(source = "customerDetailsId", target = "customerDetails")
    ShoppingCart toEntity(ShoppingCartDTO shoppingCartDTO);

    default ShoppingCart fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(id);
        return shoppingCart;
    }
}
