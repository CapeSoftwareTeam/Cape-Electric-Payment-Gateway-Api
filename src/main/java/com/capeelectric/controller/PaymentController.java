package com.capeelectric.controller;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//github.com/CapeSoftwareTeam/Cape-Electric-Payment-Gateway-Api.git
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//github.com/CapeSoftwareTeam/Cape-Electric-Payment-Gateway-Api.git

import com.capeelectric.service.PaymentService;

@RestController()
@RequestMapping("/api/v1")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping(value = "/submitPaymentDetail")
	public ResponseEntity<TreeMap<String, String>> getRedirect(@RequestParam(name = "CUST_ID") String customerId,
			@RequestParam(name = "TXN_AMOUNT") String transactionAmount,
			@RequestParam(name = "ORDER_ID") String orderId) throws Exception { 

		TreeMap<String, String> submitPayment = paymentService.submitPayment(customerId, transactionAmount, orderId);

		return new ResponseEntity<TreeMap<String, String>>(submitPayment, HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/pgresponse")
	public ResponseEntity<String> getResponseRedirect(HttpServletRequest request, Model model) throws Exception {
		paymentService.responseRedirect(request, model);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

}
