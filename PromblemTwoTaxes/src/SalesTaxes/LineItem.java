package SalesTaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;;

public class LineItem {
	public String itemName;
	public int quantity;
	public BigDecimal itemPrice; // Using Big Decimal for added precision since we are dealing with money
	public boolean taxed;
	public boolean imported;

	public LineItem(String itemName, int quantity, double itemPrice, boolean taxed, boolean imported) {
		this(itemName, quantity, new BigDecimal(itemPrice), taxed, imported);
	}

	public LineItem(String itemName, int quantity, BigDecimal itemPrice, boolean taxed, boolean imported) {
		this.itemName = itemName;
		this.quantity = quantity;
		this.taxed = taxed;
		this.imported = imported;
		this.itemPrice = itemPrice;
	}

	public double getTax() {
		// check the tax rate
		BigDecimal taxRate = new BigDecimal(taxed ? 0.1 : 0).add(new BigDecimal((imported ? 0.05 : 0)));
		// get the total
		BigDecimal lineTax = getLinePrice().multiply(taxRate);
		// round up to the nearest $0.05
		lineTax = roundUp(lineTax, new BigDecimal(0.05));

		return lineTax.doubleValue();

	}

	public BigDecimal getLinePrice() {
		return itemPrice.multiply(new BigDecimal(quantity));
	}

	/**
	 * 
	 * @param value
	 * @param increment
	 * @return return the value rounded up to the nearest increment
	 */
	public static BigDecimal roundUp(BigDecimal value, BigDecimal increment) {
		if (increment.signum() == 0) {
			// 0 increment does not make much sense, but prevent division by 0
			return value;
		} else {
			BigDecimal divided = value.divide(increment, 0, RoundingMode.UP);
			BigDecimal result = divided.multiply(increment);
			return result;
		}
	}
}
