package com.commerce.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartMapperTest {

    private ShoppingCartMapper shoppingCartMapper;

    @BeforeEach
    public void setUp() {
        shoppingCartMapper = new ShoppingCartMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(shoppingCartMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shoppingCartMapper.fromId(null)).isNull();
    }
}
