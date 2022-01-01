package io.github.cntmin81.mytodo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.cntmin81.mytodo.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	List<Customer> findByLastName(String lastName);
	Customer findById(long id);

}
