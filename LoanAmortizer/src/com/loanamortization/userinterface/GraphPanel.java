package com.loanamortization.userinterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author prashant kagwad
 * 
 *         GraphPanel class creats the background for the creation of a graph in
 *         the main ui screen.
 * 
 */
@SuppressWarnings("serial")
public class GraphPanel extends JPanel {

	private int padding = 25;
	private int labelPadding = 25;
	private Color lineColor = new Color(44, 102, 230, 180);
	private Color pointColor = new Color(100, 100, 100, 180);
	private Color gridColor = new Color(200, 200, 200, 200);
	private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private int pointWidth = 2;
	private int numberYDivisions = 5;
	private List<Double> payments;

	public GraphPanel(List<Double> payments) {
		this.payments = payments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 
	 * paintComponent() - helps the drawing the graph components such as the
	 * x-axis , y-axis, white background screen, hatch marks, grid lines
	 */
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		double xScale = ((double) getWidth() - (2 * padding) - labelPadding)
				/ (payments.size() - 1);
		double yScale = ((double) getHeight() - 2 * padding - labelPadding)
				/ (getMaxScore() - getMinScore());

		List<Point> graphPoints = new ArrayList<>();
		for (int i = 0; i < payments.size(); i++) {

			int x1 = (int) (i * xScale + padding + labelPadding);
			int y1 = (int) ((getMaxScore() - payments.get(i)) * yScale + padding);
			graphPoints.add(new Point(x1, y1));
		}

		// draw white background
		graphics2D.setColor(Color.WHITE);
		graphics2D.fillRect(padding + labelPadding, padding, getWidth()
				- (2 * padding) - labelPadding, getHeight() - 2 * padding
				- labelPadding);
		graphics2D.setColor(Color.BLACK);

		// create hatch marks and grid lines for y axis.
		for (int i = 0; i < numberYDivisions + 1; i++) {

			int x0 = padding + labelPadding;
			int x1 = pointWidth + padding + labelPadding;
			int y0 = getHeight()
					- ((i * (getHeight() - padding * 2 - labelPadding))
							/ numberYDivisions + padding + labelPadding);
			int y1 = y0;
			if (payments.size() > 0) {

				graphics2D.setColor(gridColor);
				graphics2D.drawLine(padding + labelPadding + 1 + pointWidth,
						y0, getWidth() - padding, y1);
				graphics2D.setColor(Color.BLACK);

				String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore())
						* ((i * 1.0) / numberYDivisions)) * 100))
						/ 100.0 + "";
				FontMetrics metrics = graphics2D.getFontMetrics();

				int labelWidth = metrics.stringWidth(yLabel);
				graphics2D.drawString(yLabel, x0 - labelWidth - 5, y0
						+ (metrics.getHeight() / 2) - 3);
			}
			graphics2D.drawLine(x0, y0, x1, y1);
		}

		// create hatch marks and grid lines for x axis
		for (int i = 0; i < payments.size(); i++) {
			if (payments.size() > 1) {

				int x0 = i * (getWidth() - padding * 2 - labelPadding)
						/ (payments.size() - 1) + padding + labelPadding;
				int x1 = x0;
				int y0 = getHeight() - padding - labelPadding;
				int y1 = y0 - pointWidth;
				if ((i % ((int) ((payments.size() / 20.0)) + 1)) == 0) {

					graphics2D.setColor(gridColor);
					graphics2D.drawLine(x0, getHeight() - padding
							- labelPadding - 1 - pointWidth, x1, padding);
					graphics2D.setColor(Color.BLACK);

					String xLabel = i + "";
					FontMetrics metrics = graphics2D.getFontMetrics();

					int labelWidth = metrics.stringWidth(xLabel);
					graphics2D.drawString(xLabel, x0 - labelWidth / 2, y0
							+ metrics.getHeight() + 3);
				}
				graphics2D.drawLine(x0, y0, x1, y1);
			}
		}

		// create x and y axes
		graphics2D.drawLine(padding + labelPadding, getHeight() - padding
				- labelPadding, padding + labelPadding, padding);
		graphics2D.drawLine(padding + labelPadding, getHeight() - padding
				- labelPadding, getWidth() - padding, getHeight() - padding
				- labelPadding);

		// setting line color
		Stroke oldStroke = graphics2D.getStroke();
		graphics2D.setColor(lineColor);
		graphics2D.setStroke(GRAPH_STROKE);

		for (int i = 0; i < graphPoints.size() - 1; i++) {
			int x1 = graphPoints.get(i).x;
			int y1 = graphPoints.get(i).y;
			int x2 = graphPoints.get(i + 1).x;
			int y2 = graphPoints.get(i + 1).y;
			graphics2D.drawLine(x1, y1, x2, y2);
		}

		graphics2D.setStroke(oldStroke);
		graphics2D.setColor(pointColor);
		for (int i = 0; i < graphPoints.size(); i++) {
			int x = graphPoints.get(i).x - pointWidth / 2;
			int y = graphPoints.get(i).y - pointWidth / 2;
			int ovalW = pointWidth;
			int ovalH = pointWidth;
			graphics2D.fillOval(x, y, ovalW, ovalH);
		}
	}

	/**
	 * getMinScore() - gets the min level to be drawn
	 */
	private double getMinScore() {

		double minScore = Double.MAX_VALUE;
		for (Double score : payments) {

			minScore = Math.min(minScore, score);
		}
		return minScore;
	}

	/**
	 * getMaxScore() - gets the max level to be drawn
	 */
	private double getMaxScore() {

		double maxScore = Double.MIN_VALUE;
		for (Double score : payments) {

			maxScore = Math.max(maxScore, score);
		}
		return maxScore;
	}

	/**
	 * setScores() - sets the levels
	 */
	public void setPayments(List<Double> payments) {
		this.payments = payments;

		invalidate();
		this.repaint();
	}

	/**
	 * getScores() - gets the payments as the levels
	 */
	public List<Double> getc() {
		return payments;
	}

}