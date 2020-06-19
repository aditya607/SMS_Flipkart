package com.flipkart.service;

import com.flipkart.exception.InvalidCredential;

public interface SMSOperationInterface {
	public void CheckUser(String username,String Password)throws InvalidCredential;
}
