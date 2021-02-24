package com.commerce.app.service.impl;

import com.commerce.app.service.CustomerDetailsService;
import com.commerce.app.domain.CustomerDetails;
import com.commerce.app.repository.CustomerDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerDetails}.
 */
@Service
@Transactional
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    private final Logger log = LoggerFactory.getLogger(CustomerDetailsServiceImpl.class);

    private final CustomerDetailsRepository customerDetailsRepository;

    public CustomerDetailsServiceImpl(CustomerDetailsRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
    }

    @Override
    public CustomerDetails save(CustomerDetails customerDetails) {
        log.debug("Request to save CustomerDetails : {}", customerDetails);
        return customerDetailsRepository.save(customerDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDetails> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerDetails");
        return customerDetailsRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDetails> findOne(Long id) {
        log.debug("Request to get CustomerDetails : {}", id);
        return customerDetailsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerDetails : {}", id);
        customerDetailsRepository.deleteById(id);
    }
}
