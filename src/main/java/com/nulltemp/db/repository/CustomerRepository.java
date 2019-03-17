package com.nulltemp.db.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.nulltemp.db.entity.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	List<Customer> findByLastName(@Param("lastName") String lastName);
}
