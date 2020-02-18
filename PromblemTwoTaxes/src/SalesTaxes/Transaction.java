package SalesTaxes;

import java.util.ArrayList;

public class Transaction {
	public ArrayList<LineItem> lines;

	public Transaction() {
		lines = new ArrayList<LineItem>();
	}

	public void addItem(String itemName, int quantity, double itemPrice, boolean taxed, boolean imported) {
		addItem(new LineItem(itemName, quantity, itemPrice, taxed, imported));
	}

	public void addItem(LineItem item) {
		lines.add(item);
	}

	public String getReceipt() {
		String receipt = "";
		double TaxTotal = 0;
		double Total = 0;

		// If I were to extend the program there would be separate functions for getting
		// Tax Total and Totals but since we just need to output a receipt it's more
		// efficient to just do everything in the one loop
		for (LineItem li : lines) {
			receipt += String.format("%d \t %s :\t %.2f \r\n", li.quantity, li.itemName, li.itemPrice);
			TaxTotal += li.getTax();
			Total += li.getTax() + li.getLinePrice().doubleValue();
		}
		receipt += String.format("Sales Taxes:\t %.2f\n", TaxTotal);
		receipt += String.format("Total:\t %.2f ", Total);

		return receipt;
	}
}
