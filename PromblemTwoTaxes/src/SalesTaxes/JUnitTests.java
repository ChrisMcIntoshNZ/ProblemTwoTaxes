package SalesTaxes;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTests {

	@Test
	public void test01() {
		LineItem line = new LineItem("GameBoy", 1, 10.50, true, false);
		assertEquals(1.05, line.getTax(), 0.0001);

	}

	@Test
	public void test02() {
		LineItem line = new LineItem("GameBoy", 1, 10.01, true, false);
		assertEquals(1.05, line.getTax(), 0.0001);
	}

	@Test
	public void test03() {
		LineItem line = new LineItem("GameBoy", 1, 0, true, false);
		assertEquals(0, line.getTax(), 0.0001);
	}

}
