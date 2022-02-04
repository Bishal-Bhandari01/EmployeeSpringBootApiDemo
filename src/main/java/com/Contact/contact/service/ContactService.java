package com.Contact.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import javax.transaction.Transactional;

import com.Contact.contact.controller.EmployeeResponseList;
import com.Contact.contact.dto.Employeedata;
import com.Contact.contact.dto.EmployeedataUpdate;
import com.Contact.contact.dto.Employeedataresponse;
import com.Contact.contact.entity.Employee;
import com.Contact.contact.repository.EmployeeRepository;

@Service
public class ContactService {
	
	@Autowired
	private EmployeeRepository emprepo;

	public EmployeeResponseList getAll() {
		
		List<Employeedataresponse> empresponse = new ArrayList<>();
		
		List<Employee> emp = (List<Employee>) emprepo.findAll();
		
		for(Employee emps : emp) {
			empresponse.add(getEmployeedataresponse(emps));
		}
		
		EmployeeResponseList empresplist = new EmployeeResponseList();
		empresplist.setData(empresponse);
				
		return empresplist;
		
	}

	private Employeedataresponse getEmployeedataresponse(Employee emps) {
		
		Employeedataresponse empresp = new Employeedataresponse();
		empresp.setId(emps.getId());
		empresp.setFirstname(emps.getFirstname());
		empresp.setLastname(emps.getLastname());
		empresp.setEmail(emps.getEmail());
		empresp.setPassword(emps.getPassword());
		empresp.setMobileNumber(emps.getMobileNumber());
		empresp.setLevel(emps.getLevel());
		
		return empresp;
	}

	public Employeedataresponse addemployee(Employeedata request) {
		
		Employee emp = new Employee();
		emp.setFirstname(request.getFirstname());
		emp.setLastname(request.getLastname());
		emp.setEmail(request.getEmail());
		emp.setPassword(request.getPassword());
		emp.setMobileNumber(request.getMobileNumber());
		emp.setLevel(request.getLevel());
		
		Employee empd=emprepo.save(emp);
		
		return getEmployeedataresponse(empd);
	}

	@Transactional
	public void deleteAll(Long id) {

		emprepo.deleteAllById(id);
		
	}

	public Employee updateAll(Long id, EmployeedataUpdate request) {
		
		Optional<Employee> emp = emprepo.findById(id);
		if(emp.isPresent()) {
			Employee empda = emp.get();
			empda.setFirstname(request.getFirstname());
			empda.setLastname(request.getLastname());
			empda.setEmail(request.getEmail());
			empda.setPassword(request.getPassword());
			empda.setMobileNumber(request.getMobileNumber());
			empda.setLevel(request.getLevel());
			
			return emprepo.save(empda);
			
		}
		
		return null;
		
	}

}
