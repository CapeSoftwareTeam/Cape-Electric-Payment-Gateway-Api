package com.capeelectric.model;

import java.io.Serializable;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int statusCode;
	private RazorPay razorPay;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public RazorPay getRazorPay() {
		return razorPay;
	}

	public void setRazorPay(RazorPay razorPay) {
		this.razorPay = razorPay;
	}

}
