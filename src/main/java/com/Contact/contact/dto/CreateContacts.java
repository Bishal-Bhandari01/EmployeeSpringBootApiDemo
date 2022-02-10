package com.Contact.contact.dto;

import java.util.List;

public class CreateContacts extends Contactdata {

	private List<Contactdata> contacts;

	public List<Contactdata> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contactdata> contacts) {
		this.contacts = contacts;
	}	
	
}
