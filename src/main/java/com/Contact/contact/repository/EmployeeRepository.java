package com.Contact.contact.repository;

import org.springframework.data.repository.CrudRepository;

import com.Contact.contact.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	void deleteAllById(Long id);

}
