package com.Contact.contact.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Contact.contact.entity.Contact;
import com.Contact.contact.entity.Employee;

public interface SubEmpRepository extends CrudRepository<Contact, Long> {

	void deleteByEmployeeId(Long id);

	List<Contact> findByEmployeeId(Long id);

	void deleteById(Long id);

	void deleteByIdInAndEmployeeId(List<Long> employee_id, Long id);

    void deleteDistinctByIdAndEmployeeId(Long employee_id, Long id);

	List<Contact> findByEmployeeId(Employee employee);
}
