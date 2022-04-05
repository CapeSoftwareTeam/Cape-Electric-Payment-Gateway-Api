package com.capeelectric.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Configuration
public class PaymentConfig {

	private static final String SECRET_ID = "rzp_test_wxVWzTGIN9qx5w";
	private static final String SECRET_KEY = "e0Pwj9SxvLuyTqOn3wbcVALG";

	public static String getSecretId() {
		return SECRET_ID;
	}

	public static String getSecretKey() {
		return SECRET_KEY;
	}

}
