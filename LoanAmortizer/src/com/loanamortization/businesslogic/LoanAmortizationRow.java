package com.loanamortization.businesslogic;

/**
 * @author prashant kagwad
 * 
 *         LoanAmortizationRow class contains the structure to hold the values
 *         of the row of a loan amortization table.
 * 
 */
public class LoanAmortizationRow {

	private double interest;
	private double principal;
	private double balance;

	public LoanAmortizationRow() {
		// TODO Auto-generated constructor stub
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
