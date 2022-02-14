package com.Contact.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Contact.contact.dto.Employeedata;
import com.Contact.contact.dto.EmployeedataUpdate;
import com.Contact.contact.dto.Employeedataresponse;

import java.util.List;

import com.Contact.contact.dto.ContactResponse;
import com.Contact.contact.dto.CreateContacts;
import com.Contact.contact.service.EmployeeService;
import com.Contact.contact.entity.Employee;

@RestController
@RequestMapping("/data")
public class mycontroller {
	
	@Autowired
	public EmployeeService empservice;
	
	/**
	 * Default content type application/json
	 * 
	 * @param request
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employeedataresponse addEmployee(@RequestBody Employeedata request) {
		return empservice.addemployee(request);
	}
	
	@PostMapping("/{id}/contacts")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employeedataresponse addEmployee(@PathVariable Long id,@RequestBody CreateContacts request) {
		return empservice.savesubemp(id, request);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public EmployeeResponseList getAll() {
		return empservice.getAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		empservice.deleteAll(id);
	}
	
	@DeleteMapping("/{employee_id}/delcontact")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletesingle(@PathVariable Long employee_id, @RequestBody Long id) {
		empservice.deletesingle(employee_id, id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee update(@PathVariable Long id, @RequestBody EmployeedataUpdate request) {
		return empservice.updateAll(id, request);
	}
	
	@PutMapping("/{id}/contact")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Contact update(@PathVariable Long id, @RequestBody ContactUpdate contact) {
		return empservice.updateContact(id, contact);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Employeedata get(@PathVariable Long id) {
		return empservice.get(id);
	}

	
	@DeleteMapping("/{employee_id}/deletecontact")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteContact(@PathVariable Long employee_id, @RequestBody List<Long> id){
		empservice.deletemultiple(employee_id, id);
	}

	@DeleteMapping("deleteall/{employee_id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAllcontact(@PathVariable Long employee_id){
		empservice.deleteAllcontact(employee_id);
	}

	@GetMapping("/getallcontact/{employee_id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ContactResponse getcontact(@PathVariable Long employee_id){
		return empservice.getcontact(employee_id);
	}
	
}
