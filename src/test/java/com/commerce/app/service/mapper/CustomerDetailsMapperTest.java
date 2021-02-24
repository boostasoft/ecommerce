package com.commerce.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerDetailsMapperTest {

    private CustomerDetailsMapper customerDetailsMapper;

    @BeforeEach
    public void setUp() {
        customerDetailsMapper = new CustomerDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(customerDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(customerDetailsMapper.fromId(null)).isNull();
    }
}
