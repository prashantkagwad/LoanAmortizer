package com.loanamortization.businesslogic;

/**
 * @author prashant kagwad
 * 
 *         LoanAmortizationCalculator class contains the business login to
 *         compute the components of the loan amortization table.
 * 
 */
public class LoanAmortizationCalculator {

	public LoanAmortizationCalculator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * calculateLoan() : Calculates the loan amortization table along with
	 * monthly payments and total payment. Stores loan amortization table
	 * contents into LoanAmortizationRow object, monthly payments into
	 * MonthlyPayments field and total payments into TotalPayment field of
	 * LoanAmortizationTable object.
	 * 
	 * @param loanAmount
	 * @param numberOfMonths
	 * @param rate
	 * @return
	 */
	public LoanAmortizationTable calculateLoan(double loanAmount,
			int numberOfMonths, double rate) {

		LoanAmortizationTable table = new LoanAmortizationTable();
		LoanAmortizationRow[] rows = new LoanAmortizationRow[numberOfMonths];

		// Computing monthly payment and total payment
		double annualInterestRate = (rate) / 100;
		double monthlyInterestRate = annualInterestRate / 12;
		double monthlyPayment = loanAmount
				* (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate,
						-numberOfMonths)));
		double totalPayment = monthlyPayment * numberOfMonths;
		double balance = loanAmount;
		double interest;
		double principal;

		// Computing the amortization table
		// System.out.println("Payment#\t" + "Interest\t" + "Principal\t"
		// + "Balance\n\n");
		for (int i = 0; i < numberOfMonths; i++) {
			rows[i] = new LoanAmortizationRow();

			interest = (int) (monthlyInterestRate * balance * 100) / 100.0;
			principal = (int) ((monthlyPayment - interest) * 100) / 100.0;
			balance = (int) ((balance - principal) * 100) / 100.0;

			// setting the values into the LoanAmortizationRow object
			if (interest > 0)
				rows[i].setInterest(interest);
			else
				rows[i].setInterest(0);

			if (balance > 0)
				rows[i].setBalance(balance);
			else
				rows[i].setBalance(0);

			if (principal > 0)
				rows[i].setPrincipal(principal);
			else
				rows[i].setPrincipal(0);

			// System.out.println(i + 1 + "\t" + rows[i].getInterest() + "\t"
			// + rows[i].getPrincipal() + "\t" + rows[i].getBalance()
			// + "\n");

		}

		table.setRow(rows);
		table.setMonthlyPayment((int) (monthlyPayment * 100) / 100.0);
		table.setTotalPayment((int) (totalPayment * 100) / 100.0);

		// System.out.println("\n\nMonthly Payment: $"
		// + (int) (monthlyPayment * 100) / 100.0 + "\n");
		// System.out.println("Total Payment: $" + (int) (totalPayment * 100)
		// / 100.0 + "\n\n");

		return table;
	}
}
