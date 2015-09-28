package com.loanamortization.controller;

import com.loanamortization.businesslogic.LoanAmortizationCalculator;
import com.loanamortization.businesslogic.LoanAmortizationTable;

/**
 * @author prashant kagwad
 * 
 *         LoanAmortizerController class is the controller class for the data
 *         exchange between the ui layer and business logic layer. 
 * 
 */
public class LoanAmortizerController {

	public LoanAmortizerController() {
		// TODO Auto-generated constructor stub
	}

	public LoanAmortizationTable computeTable(double loanAmount,
			int numberOfMonths, double rate) {

		LoanAmortizationCalculator calculator = new LoanAmortizationCalculator();
		return calculator.calculateLoan(loanAmount, numberOfMonths, rate);
	}
}
