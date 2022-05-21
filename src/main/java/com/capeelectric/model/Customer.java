package com.capeelectric.model;

import java.io.Serializable;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String customerName;
	private String email;
	private String phoneNumber;
	private String amount;
	private String inspectorRegisterId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getInspectorRegisterId() {
		return inspectorRegisterId;
	}

	public void setInspectorRegisterId(String inspectorRegisterId) {
		this.inspectorRegisterId = inspectorRegisterId;
	}

}
