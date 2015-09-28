package com.loanamortization.businesslogic;

/**
 * @author prashant kagwad
 * 
 *         LoanAmortizationTable class contains the structure to hold the values
 *         of a loan amortization table.
 * 
 */
public class LoanAmortizationTable {

	private int numberOfPayments;
	private double monthlyPayment;
	private double totalPayment;
	private LoanAmortizationRow[] row;

	public LoanAmortizationTable() {
		// TODO Auto-generated constructor stub
	}

	public int getNumberOfPayments() {
		return numberOfPayments;
	}

	public void setNumberOfPayments(int numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public LoanAmortizationRow[] getRow() {
		return row;
	}

	public void setRow(LoanAmortizationRow[] row) {
		this.row = row;
	}

}
