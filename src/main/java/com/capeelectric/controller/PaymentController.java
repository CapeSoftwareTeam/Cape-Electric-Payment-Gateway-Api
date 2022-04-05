package com.capeelectric.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.config.PaymentConfig;
import com.capeelectric.model.Customer;
import com.capeelectric.model.RazorPay;
import com.capeelectric.model.Response;
import com.google.gson.Gson;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

	private RazorpayClient client;
	private static Gson gson = new Gson();

	public PaymentController() throws RazorpayException {
		this.client = new RazorpayClient(PaymentConfig.getSecretId(), PaymentConfig.getSecretKey());
	}

	@PostMapping(value = "/createPayment")
	public ResponseEntity<String> createOrder(@RequestBody Customer customer) {

		try {

			Order order = createRazorPayOrder(customer.getAmount());
			RazorPay razorPay = getRazorPay((String) order.get("id"), customer);

			return new ResponseEntity<String>(gson.toJson(getResponse(razorPay, 200)), HttpStatus.OK);
		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(gson.toJson(getResponse(new RazorPay(), 500)), HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping(value = "/fetchOrder/{orderId}")
	public ResponseEntity<String> fetchOrder(@PathVariable String orderId) throws RazorpayException {

		Order fetch = client.Orders.fetch(orderId);
		return new ResponseEntity<String>(fetch.get("status").toString(), HttpStatus.OK);
	}

	private Response getResponse(RazorPay razorPay, int statusCode) {
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setRazorPay(razorPay);
		return response;
	}

	private RazorPay getRazorPay(String orderId, Customer customer) {
		RazorPay razorPay = new RazorPay();
		 razorPay.setApplicationFee(convertRupeeToPaise(customer.getAmount()));
		 razorPay.setCustomerName(customer.getCustomerName());
		 razorPay.setCustomerEmail(customer.getEmail());
		// razorPay.setMerchantName("Test");
		// razorPay.setPurchaseDescription("TEST PURCHASES");
		razorPay.setRazorpayOrderId(orderId);
		razorPay.setSecretKey(PaymentConfig.getSecretId());
		// razorPay.setImageURL("/logo");
		// razorPay.setTheme("#F37254");
		razorPay.setNotes("notes" + orderId);
		razorPay.setInspectorRegisterId(customer.getInspectorRegisterId());
		System.out.println(customer.getInspectorRegisterId());

		return razorPay;
	}

	private Order createRazorPayOrder(String amount) throws RazorpayException {

		JSONObject options = new JSONObject();
		options.put("amount",convertRupeeToPaise(amount));
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
		return client.Orders.create(options);
	}

	private String convertRupeeToPaise(String paise) {
		BigDecimal b = new BigDecimal(paise);
		BigDecimal value = b.multiply(new BigDecimal("100"));
		return value.setScale(0, RoundingMode.UP).toString();

	}

}
