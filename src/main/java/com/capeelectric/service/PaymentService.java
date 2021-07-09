package com.capeelectric.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface PaymentService {

	public ModelAndView submitPayment(String customerId, String transactionAmount, String orderId) throws Exception;

	public String responseRedirect(HttpServletRequest request, Model model) throws Exception;
}
