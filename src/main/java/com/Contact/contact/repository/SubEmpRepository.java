package com.Contact.contact.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Contact.contact.entity.Employee;
import com.Contact.contact.entity.Contact;

public interface SubEmpRepository extends CrudRepository<Contact, Long> {

	void deleteByEmployeeId(Long id);

	List<Contact> findByEmployeeId(Long id);

	Optional<Contact> findByUserId(Long id);	
}
