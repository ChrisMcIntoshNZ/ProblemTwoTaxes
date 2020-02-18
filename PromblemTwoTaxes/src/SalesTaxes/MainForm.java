package SalesTaxes;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;

public class MainForm {

	protected Shell shlCheckout;
	private Text txtPrice;
	private Text txtItemName;
	private Transaction transaction = new Transaction();
	private Text txtQuantity;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainForm window = new MainForm();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCheckout.open();
		shlCheckout.layout();
		while (!shlCheckout.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCheckout = new Shell();
		shlCheckout.setSize(560, 300);
		shlCheckout.setText("Checkout");

		txtPrice = new Text(shlCheckout, SWT.BORDER);
		txtPrice.setBounds(143, 33, 54, 21);

		txtItemName = new Text(shlCheckout, SWT.BORDER);
		txtItemName.setBounds(10, 33, 127, 21);

		Button chkTaxFree = new Button(shlCheckout, SWT.CHECK);
		chkTaxFree.setBounds(282, 35, 63, 16);
		chkTaxFree.setText("Tax Free");

		Button chkImported = new Button(shlCheckout, SWT.CHECK);
		chkImported.setBounds(354, 35, 93, 16);
		chkImported.setText("Imported");

		Button btnAddItem = new Button(shlCheckout, SWT.NONE);
		btnAddItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				MessageBox m = new MessageBox(shlCheckout);
				int qty = 0;
				double itemPrice = 0;
				String s = txtQuantity.getText();
				if (txtQuantity.getText().matches("-?(0|[1-9]\\d*)")) {
					qty = Integer.parseInt(txtQuantity.getText());
				} else {
					m.setMessage("Invalid Quantity");
					m.open();
					return;
				}
				if (txtPrice.getText().matches("^\\d+\\.\\d{0,2}$")) {
					itemPrice = Double.parseDouble(txtPrice.getText());
				} else {
					m.setMessage("Invalid Price");
					m.open();
					return;
				}

				transaction.addItem(txtItemName.getText(), qty, itemPrice, !chkTaxFree.getSelection(),
						chkImported.getSelection());

				txtItemName.setText("");
				txtPrice.setText("");
				txtQuantity.setText("");
				chkTaxFree.setSelection(false);
				chkImported.setSelection(false);

			}
		});

		btnAddItem.setBounds(459, 31, 75, 25);
		btnAddItem.setText("Add");

		Label lblReceipt = new Label(shlCheckout, SWT.NONE);
		lblReceipt.setBounds(10, 101, 524, 150);

		Button btnPrintReceipt = new Button(shlCheckout, SWT.NONE);
		btnPrintReceipt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblReceipt.setText(transaction.getReceipt());
			}
		});

		btnPrintReceipt.setBounds(10, 70, 75, 25);
		btnPrintReceipt.setText("Print Receipt");

		txtQuantity = new Text(shlCheckout, SWT.BORDER);
		txtQuantity.setBounds(205, 33, 47, 21);

		Label lblIrwm = new Label(shlCheckout, SWT.NONE);
		lblIrwm.setBounds(10, 10, 55, 15);
		lblIrwm.setText("Item");

		Label lblPirce = new Label(shlCheckout, SWT.NONE);
		lblPirce.setBounds(143, 10, 55, 15);
		lblPirce.setText("Pirce");

		Label lblQuantity = new Label(shlCheckout, SWT.NONE);
		lblQuantity.setBounds(205, 10, 55, 15);
		lblQuantity.setText("Quantity");

		Button btnNewTransaction = new Button(shlCheckout, SWT.NONE);
		btnNewTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				transaction = new Transaction();
				lblReceipt.setText("");
			}
		});
		btnNewTransaction.setBounds(426, 70, 108, 25);
		btnNewTransaction.setText("New Transaction");
		shlCheckout.setTabList(new Control[] { txtItemName, txtPrice, txtQuantity, chkTaxFree, chkImported, btnAddItem,
				btnPrintReceipt });

	}
}
