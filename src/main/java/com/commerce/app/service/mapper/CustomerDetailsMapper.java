package com.commerce.app.service.mapper;


import com.commerce.app.domain.*;
import com.commerce.app.service.dto.CustomerDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerDetails} and its DTO {@link CustomerDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CustomerDetailsMapper extends EntityMapper<CustomerDetailsDTO, CustomerDetails> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    CustomerDetailsDTO toDto(CustomerDetails customerDetails);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "removeCart", ignore = true)
    CustomerDetails toEntity(CustomerDetailsDTO customerDetailsDTO);

    default CustomerDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setId(id);
        return customerDetails;
    }
}
