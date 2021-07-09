package com.capeelectric.service;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface PaymentService {

	public TreeMap<String, String> submitPayment(String customerId, String transactionAmount, String orderId) throws Exception;

	public String responseRedirect(HttpServletRequest request, Model model) throws Exception;
}
