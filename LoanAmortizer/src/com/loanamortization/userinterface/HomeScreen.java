package com.loanamortization.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.loanamortization.businesslogic.LoanAmortizationRow;
import com.loanamortization.businesslogic.LoanAmortizationTable;
import com.loanamortization.controller.LoanAmortizerController;

/**
 * @author prashant kagwad
 * 
 *         HomeScreen class - helps in creating the references for all the
 *         components, initializing the components with settings, does the
 *         validation on the entered data. Sends this data to the business logic
 *         layer (domain) through the controller class. Gets the loan
 *         amortization table components back from the controller and displays
 *         as table or graph based on the user perference.
 * 
 */

public class HomeScreen {

	private JFrame frmLoanAmortizater;
	private JTextPane txtpnLoanAmortizationProgram;
	private JDesktopPane desktopPane;
	private JSeparator separatorTop;

	private JTextField principleAmount;
	private JLabel lblPrincipalAmount;
	private JLabel lblErrorPA;
	private boolean principleAmountFlag = false;

	private JTextField term;
	private JLabel lblTermMonths;
	private JLabel lblErrorTerm;
	private boolean termFlag = false;

	private JTextField interestRate;
	private JLabel lblInterestRate;
	private JLabel lblErrorIR;
	private boolean interestRateFlag = false;

	private JButton btnDisplayChart;
	private JButton btnDisplayGraph;
	private JScrollPane scrollPane;
	private JTable tableLoanTable;
	private JSeparator separatorDown;

	private double tempPrincipalAmount;
	private int tempTerm;
	private double tempInterestRate;

	private JTextField textMonthlyPayment;
	private JTextField textTotalPayment;
	private JLabel lblMonthlyPayment;
	private JLabel lblTotalPayment;

	/**
	 * launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					HomeScreen window = new HomeScreen();
					window.frmLoanAmortizater.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * create the application.
	 */
	public HomeScreen() {
		initialize();
	}

	/**
	 * initialize the contents of the frame.
	 */
	private void initialize() {

		setCompnents();
		validatePrincipleAmount();
		validateTerm();
		validateInterestRate();
		displayTable();
		displayGraph();

	}

	/**
	 * setCompnents() - sets all the components with the initial content
	 * settings and data setting where ever required.
	 */
	private void setCompnents() {

		frmLoanAmortizater = new JFrame();
		frmLoanAmortizater.setResizable(false);
		frmLoanAmortizater.setTitle("Loan Amortizater");
		frmLoanAmortizater.setBounds(550, 300, 552, 330);
		frmLoanAmortizater.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txtpnLoanAmortizationProgram = new JTextPane();
		txtpnLoanAmortizationProgram.setEditable(false);
		txtpnLoanAmortizationProgram.setFont(new Font("Calibri Light",
				Font.ITALIC, 41));
		txtpnLoanAmortizationProgram.setText("    Loan Amortization Program");
		frmLoanAmortizater.getContentPane().add(txtpnLoanAmortizationProgram,
				BorderLayout.NORTH);

		desktopPane = new JDesktopPane();
		frmLoanAmortizater.getContentPane().add(desktopPane,
				BorderLayout.CENTER);
		desktopPane.setBackground(Color.WHITE);

		separatorTop = new JSeparator();
		separatorTop.setBounds(0, 190, 546, 5);
		desktopPane.add(separatorTop);

		// principle amount components
		lblPrincipalAmount = new JLabel("Principal Amount");
		lblPrincipalAmount.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		lblPrincipalAmount.setLabelFor(lblPrincipalAmount);
		lblPrincipalAmount.setBounds(128, 22, 159, 14);
		desktopPane.add(lblPrincipalAmount);

		principleAmount = new JTextField();
		principleAmount.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		principleAmount.setToolTipText("Enter Amount");
		principleAmount.setBounds(297, 19, 86, 20);
		desktopPane.add(principleAmount);

		lblErrorPA = new JLabel("");
		lblErrorPA.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		lblErrorPA.setEnabled(false);
		lblErrorPA.setBounds(297, 53, 212, 14);
		desktopPane.add(lblErrorPA);

		// term components
		lblTermMonths = new JLabel("Term ( Months )");
		lblTermMonths.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		lblTermMonths.setLabelFor(lblTermMonths);
		lblTermMonths.setBounds(128, 81, 159, 14);
		desktopPane.add(lblTermMonths);

		term = new JTextField();
		term.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		term.setToolTipText("Term of Loan in Months");
		term.setBounds(297, 78, 86, 20);
		desktopPane.add(term);

		lblErrorTerm = new JLabel("");
		lblErrorTerm.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		lblErrorTerm.setEnabled(false);
		lblErrorTerm.setBounds(297, 110, 212, 14);
		desktopPane.add(lblErrorTerm);

		// interest rate components
		lblInterestRate = new JLabel("Interest Rate ( Annual )");
		lblInterestRate.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		lblInterestRate.setLabelFor(lblInterestRate);
		lblInterestRate.setBounds(128, 140, 159, 14);
		desktopPane.add(lblInterestRate);

		interestRate = new JTextField();
		interestRate.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		interestRate.setToolTipText("Annual Interest Rate ");
		interestRate.setBounds(297, 134, 86, 20);
		desktopPane.add(interestRate);

		lblErrorIR = new JLabel("");
		lblErrorIR.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		lblErrorIR.setEnabled(false);
		lblErrorIR.setBounds(297, 165, 212, 14);
		desktopPane.add(lblErrorIR);

		// buttons for displaying the contents of tables and graph
		btnDisplayChart = new JButton("Display Chart");
		btnDisplayChart.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		btnDisplayChart.setEnabled(false);
		btnDisplayChart.setBounds(101, 206, 122, 23);
		desktopPane.add(btnDisplayChart);

		btnDisplayGraph = new JButton("Display Graph");
		btnDisplayGraph.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		btnDisplayGraph.setEnabled(false);
		btnDisplayGraph.setBounds(320, 206, 122, 23);
		desktopPane.add(btnDisplayGraph);

		separatorDown = new JSeparator();
		separatorDown.setBounds(0, 237, 546, 5);
		desktopPane.add(separatorDown);

		// monthly payments and total payments
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 281, 526, 262);
		desktopPane.add(scrollPane);

		textMonthlyPayment = new JTextField();
		textMonthlyPayment.setEditable(false);
		textMonthlyPayment.setToolTipText("");
		textMonthlyPayment.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		textMonthlyPayment.setBounds(174, 250, 86, 20);
		desktopPane.add(textMonthlyPayment);

		textTotalPayment = new JTextField();
		textTotalPayment.setEditable(false);
		textTotalPayment.setToolTipText("");
		textTotalPayment.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		textTotalPayment.setBounds(406, 250, 86, 20);
		desktopPane.add(textTotalPayment);

		lblMonthlyPayment = new JLabel("Monthly Payment");
		lblMonthlyPayment.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		lblMonthlyPayment.setBounds(44, 253, 114, 14);
		desktopPane.add(lblMonthlyPayment);

		lblTotalPayment = new JLabel("Total Payment");
		lblTotalPayment.setFont(new Font("Calibri Light", Font.ITALIC, 13));
		lblTotalPayment.setBounds(297, 253, 107, 14);
		desktopPane.add(lblTotalPayment);
	}

	/**
	 * validatePrincipleAmount() - validates the entered principle amount.
	 */
	private void validatePrincipleAmount() {

		// validation on the entered principle amount
		principleAmount.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent arg0) {

				try {
					if (!principleAmount.getText().isEmpty()) {
						tempPrincipalAmount = Double
								.parseDouble(principleAmount.getText());

						if (tempPrincipalAmount > 0) {

							principleAmountFlag = true;

							if (principleAmountFlag && termFlag
									&& interestRateFlag) {
								btnDisplayChart.setEnabled(true);
								btnDisplayGraph.setEnabled(true);
							}
						} else {
							lblErrorPA
									.setText("▲ Amount cannot be less than zero!");
							lblErrorPA.setEnabled(true);
						}
					} else {
						lblErrorPA
								.setText("▲ Amount field cannot be left blank!");
						lblErrorPA.setEnabled(true);
					}

				} catch (Exception e) {
					lblErrorPA.setText("▲ Amount has to be a numeric field!");
					lblErrorPA.setEnabled(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {

				try {

					lblErrorPA.setText("");
					lblErrorPA.setEnabled(false);

				} catch (Exception e) {

					System.out.println("Error occured when changing focus");
				}
			}
		});
	}

	/**
	 * validateTerm() - validates the entered term.
	 */
	private void validateTerm() {

		// validation on the entered term
		term.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent arg0) {

				try {
					if (!term.getText().isEmpty()) {

						tempTerm = Integer.parseInt(term.getText());
						if (tempTerm > 0) {

							termFlag = true;

							if (principleAmountFlag && termFlag
									&& interestRateFlag) {
								btnDisplayChart.setEnabled(true);
								btnDisplayGraph.setEnabled(true);
							}
						} else {
							lblErrorTerm
									.setText("▲ Term cannot be less than zero!");
							lblErrorTerm.setEnabled(true);
						}
					} else {
						lblErrorTerm
								.setText("▲ Term field cannot be left blank!");
						lblErrorTerm.setEnabled(true);
					}

				} catch (Exception e) {
					lblErrorTerm.setText("▲ Term has to be a numeric field!");
					lblErrorTerm.setEnabled(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {

				try {

					lblErrorTerm.setText("");
					lblErrorTerm.setEnabled(false);

				} catch (Exception e) {

					System.out.println("Error occured when changing focus");
				}
			}
		});
	}

	/**
	 * validateInterestRate() - validates the entered interest rate.
	 */
	private void validateInterestRate() {

		// validation on the entered interest rate
		interestRate.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent arg0) {

				try {
					if (!interestRate.getText().isEmpty()) {
						tempInterestRate = Double.parseDouble(interestRate
								.getText());

						if (tempInterestRate > 0) {

							interestRateFlag = true;

							if (principleAmountFlag && termFlag
									&& interestRateFlag) {
								btnDisplayChart.setEnabled(true);
								btnDisplayGraph.setEnabled(true);
							}
						} else {
							lblErrorIR
									.setText("▲ Rate cannot be less than zero!");
							lblErrorIR.setEnabled(true);
						}
					} else {
						lblErrorIR
								.setText("▲ Rate field cannot be left blank!");
						lblErrorIR.setEnabled(true);
					}

				} catch (Exception e) {
					lblErrorIR.setText("▲ Rate has to be a numeric field!");
					lblErrorIR.setEnabled(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {

				try {

					lblErrorIR.setText("");
					lblErrorIR.setEnabled(false);

				} catch (Exception e) {

					System.out.println("Error occured when changing focus");
				}
			}
		});
	}

	/**
	 * displayTable() - displays the loan amortization table
	 */
	private void displayTable() {

		btnDisplayChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Rectangle rectangle = frmLoanAmortizater.getBounds();
				frmLoanAmortizater
						.setBounds(rectangle.x, rectangle.y, 552, 630);

				LoanAmortizerController loanAmortizer = new LoanAmortizerController();
				LoanAmortizationTable dataTable = loanAmortizer.computeTable(
						tempPrincipalAmount, tempTerm, tempInterestRate);
				LoanAmortizationRow[] row = dataTable.getRow();

				List<String> columns = new ArrayList<String>();
				columns.add("Payment#");
				columns.add("Interest");
				columns.add("Principal");
				columns.add("Balance");

				Object[][] object = new Object[dataTable.getRow().length][4];
				for (int iterator = 0; iterator < dataTable.getRow().length; iterator++) {

					object[iterator][0] = (iterator + 1);
					object[iterator][1] = row[iterator].getInterest();
					object[iterator][2] = row[iterator].getPrincipal();
					object[iterator][3] = row[iterator].getBalance();

				}

				textMonthlyPayment.setText(String.valueOf(dataTable
						.getMonthlyPayment()));
				textTotalPayment.setText(String.valueOf(dataTable
						.getTotalPayment()));

				TableModel tableModel = new DefaultTableModel(object, columns
						.toArray());

				tableLoanTable = new JTable(tableModel);
				tableLoanTable.setEnabled(false);
				tableLoanTable.setFont(new Font("Calibri Light", Font.ITALIC,
						13));
				scrollPane.setViewportView(tableLoanTable);
			}
		});

	}

	/**
	 * displayGraph() - with the help of GraphPanel class it displays the loan
	 * amortization table
	 */
	private void displayGraph() {

		btnDisplayGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Rectangle rectangle = frmLoanAmortizater.getBounds();
				frmLoanAmortizater
						.setBounds(rectangle.x, rectangle.y, 552, 630);
				List<Double> graphBalance = new ArrayList<>();

				LoanAmortizerController loanAmortizer = new LoanAmortizerController();
				LoanAmortizationTable dataTable = loanAmortizer.computeTable(
						tempPrincipalAmount, tempTerm, tempInterestRate);
				LoanAmortizationRow[] row = dataTable.getRow();

				graphBalance.add(tempPrincipalAmount);
				for (int i = 0; i < dataTable.getRow().length; i++) {
					graphBalance.add(row[i].getBalance());
				}

				textMonthlyPayment.setText(String.valueOf(dataTable
						.getMonthlyPayment()));
				textTotalPayment.setText(String.valueOf(dataTable
						.getTotalPayment()));

				GraphPanel mainPanel = new GraphPanel(graphBalance);
				mainPanel.setFont(new Font("Calibri Light", Font.ITALIC, 13));
				mainPanel.setPreferredSize(new Dimension(500, 250));

				scrollPane.setViewportView(mainPanel);

			}
		});
	}
}
