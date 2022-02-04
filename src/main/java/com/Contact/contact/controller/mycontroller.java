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
import com.Contact.contact.service.ContactService;


@RestController
@RequestMapping("/data")
public class mycontroller {
	
	@Autowired
	private ContactService empService;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public EmployeeResponseList getAll() {
		return empService.getAll();
	}
	
	/**
	 * Default content type application/json
	 * 
	 * @param request
	 */	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employeedataresponse addemployee(@RequestBody Employeedata request) {
		return empService.addemployee(request);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		empService.deleteAll(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void update(@PathVariable Long id, @RequestBody EmployeedataUpdate request) {
		empService.updateAll(id,request);
	}
	
}
