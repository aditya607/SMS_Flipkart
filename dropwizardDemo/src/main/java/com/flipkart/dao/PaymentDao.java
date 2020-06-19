package com.flipkart.dao;

public interface PaymentDao {
	public int calculateScholarship();
	public int payByCreditCard(int totalAmount);
	public int payByDebitCard(int totalAmount);
	public void changeStatus();
}
