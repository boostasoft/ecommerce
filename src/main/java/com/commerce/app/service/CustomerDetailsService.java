package com.commerce.app.service;

import com.commerce.app.domain.CustomerDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link CustomerDetails}.
 */
public interface CustomerDetailsService {

    /**
     * Save a customerDetails.
     *
     * @param customerDetails the entity to save.
     * @return the persisted entity.
     */
    CustomerDetails save(CustomerDetails customerDetails);

    /**
     * Get all the customerDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerDetails> findAll(Pageable pageable);


    /**
     * Get the "id" customerDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerDetails> findOne(Long id);

    /**
     * Delete the "id" customerDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
