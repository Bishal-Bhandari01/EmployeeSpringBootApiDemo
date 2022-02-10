package com.Contact.contact.service;

import java.util.*;
import java.util.stream.Collector;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Contact.contact.controller.EmployeeResponseList;
import com.Contact.contact.dto.Employeedata;
import com.Contact.contact.dto.EmployeedataUpdate;
import com.Contact.contact.dto.Employeedataresponse;
import com.Contact.contact.dto.Contactdata;
import com.Contact.contact.dto.CreateContacts;
import com.Contact.contact.dto.ContactUpdate;
import com.Contact.contact.entity.Employee;
import com.Contact.contact.entity.Contact;
import com.Contact.contact.repository.EmployeeRepository;
import com.Contact.contact.repository.SubEmpRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private SubEmpRepository subempRepository;

	public Employeedataresponse addemployee(Employeedata request) {
		Employee emp = new Employee();
		emp.setFirstname(request.getFirstname());
		emp.setLastname(request.getLastname());
		emp.setEmail(request.getEmail());
		emp.setPassword(request.getPassword());
		emp.setMobileNumber(request.getMobileNumber());
		emp.setLevel(request.getLevel());
		
		Employee empd=employeeRepository.save(emp);
		
		List<Contactdata> subemp = request.getContacts();
		
		if(subemp != null) {
			
			for(int i = 0; i < subemp.size(); i++) {
				
				Contact lower = new Contact();
				lower.setEmail(subemp.get(i).getEmail());
				lower.setMobileNumber(subemp.get(i).getMobileNumber());	
				lower.setEmployee(empd);
				subempRepository.save(lower);
			}
			
		}
		return getEmployeedataresponse(empd);
	}

	private Employeedataresponse getEmployeedataresponse(Employee semp) {
		Employeedataresponse empresp = new Employeedataresponse();
		empresp.setId(semp.getId());
		empresp.setFirstname(semp.getFirstname());
		empresp.setLastname(semp.getLastname());
		empresp.setEmail(semp.getEmail());
		empresp.setPassword(semp.getPassword());
		empresp.setMobileNumber(semp.getMobileNumber());
		empresp.setLevel(semp.getLevel());
		
		List<Contact> lower = subempRepository.findByEmployeeId(semp.getId());

		List<Contactdata> subempdata = new ArrayList<>();
		
		for(Contact lowers: lower) {
			Contactdata subemdata = new Contactdata();
			subemdata.setEmail(lowers.getEmail());
			subemdata.setMobileNumber(lowers.getMobileNumber());
			subempdata.add(subemdata);
		}
		empresp.setContacts(subempdata);
		return empresp;
	}

	public EmployeeResponseList getAll() {
		List<Employeedataresponse> employeeresponse = new ArrayList<>();

		List<Employee> emp = (List<Employee>) employeeRepository.findAll();

		for (Employee emps : emp) {
			employeeresponse.add(getEmployeedataresponse(emps));
		}
		
		EmployeeResponseList resplist = new EmployeeResponseList();
		resplist.setData(employeeresponse);
		return resplist;
	}

	public Employee updateAll(Long id, EmployeedataUpdate request) {
		Optional<Employee> opt = employeeRepository.findById(id);
		if (opt.isPresent()) {
			Employee emp = opt.get();
			emp.setFirstname(request.getFirstname());
			emp.setLastname(request.getLastname());
			emp.setEmail(request.getEmail());
			emp.setPassword(request.getPassword());
			emp.setLevel(request.getLevel());
			emp.setMobileNumber(request.getMobileNumber());
			
			return employeeRepository.save(emp);
		}
		return null;
	}

	public Employeedataresponse get(Long id) {
		Optional<Employee> optemp = employeeRepository.findById(id);
		if(optemp.isPresent()) {
			return getEmployeedataresponse(optemp.get());
		}
		return null;
	}

	public Employeedataresponse savesubemp(Long id, CreateContacts request) {
		
		Optional<Employee> optemp = employeeRepository.findById(id);
		
		if(optemp.isPresent()) {
			Employee emp = optemp.get();
			for(int i = 0; i < request.getContacts().size(); i++) {
				Contact subemp = new Contact();
				subemp.setEmail(request.getContacts().get(i).getEmail());
				subemp.setMobileNumber(request.getContacts().get(i).getMobileNumber());
				subemp.setEmployee(emp);
				subempRepository.save(subemp);
			}	
			
			return getEmployeedataresponse(emp);
			
		}
		
		return null;
	}
	
	/**
	 * Implementation logic is:
	 * <p>
	 * <li>First delete the contact associated with given userId</li>
	 * <li>Then only delete the user by id</li>
	 * 
	 * @param id
	 */	
	@Transactional
	public void deleteAll(Long id) {
		subempRepository.deleteByEmployeeId(id);
		employeeRepository.deleteById(id);
	}

	public Contact updateContact(Long id, ContactUpdate contact) {
		
		Optional<Employee> optemp = employeeRepository.findById(id);
		
		if(optemp.isPresent()) {
			
			Employee emp = optemp.get();
			
			for(int i = 0; i < contact.getContacts().size(); i++) {
				Contact subemp = new Contact();
				subemp.setEmail(contact.getContacts().get(i).getEmail());
				subemp.setMobileNumber(contact.getContacts().get(i).getMobileNumber());
				subemp.setEmployee(emp);
				subempRepository.save(subemp);
			}	
			
		}
		
		return null;
		
	}

	public Contact updateemp(Long id, ContactUpdate request) {
		
		Optional<Employee> optemp = employeeRepository.findById(id);
		
		if(optemp.isPresent()) {
			
			Employee emp = optemp.get();
			
			for(int i = 0; i < request.getContacts().size(); i++) {
				Contact subemp = new Contact();
				subemp.setEmail(request.getContacts().get(i).getEmail());
				subemp.setMobileNumber(request.getContacts().get(i).getMobileNumber());
				subemp.setEmployee(emp);
				subempRepository.save(subemp);
			}	
			
		}
		return null;
	}

	
}


