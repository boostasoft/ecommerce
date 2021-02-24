package com.commerce.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.commerce.app.web.rest.TestUtil;

public class CustomerDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerDetailsDTO.class);
        CustomerDetailsDTO customerDetailsDTO1 = new CustomerDetailsDTO();
        customerDetailsDTO1.setId(1L);
        CustomerDetailsDTO customerDetailsDTO2 = new CustomerDetailsDTO();
        assertThat(customerDetailsDTO1).isNotEqualTo(customerDetailsDTO2);
        customerDetailsDTO2.setId(customerDetailsDTO1.getId());
        assertThat(customerDetailsDTO1).isEqualTo(customerDetailsDTO2);
        customerDetailsDTO2.setId(2L);
        assertThat(customerDetailsDTO1).isNotEqualTo(customerDetailsDTO2);
        customerDetailsDTO1.setId(null);
        assertThat(customerDetailsDTO1).isNotEqualTo(customerDetailsDTO2);
    }
}
