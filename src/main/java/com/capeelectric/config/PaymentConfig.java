package com.capeelectric.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Component
public class PaymentConfig {

	@Value("${secret.id}")
	private String secretId;

	@Value("${secret.key}")
	private String secretKey;

	public String getSecretId() {
		return secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
