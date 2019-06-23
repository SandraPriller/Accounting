package at.fhsalzburg.its.nos.accounting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Sandra Priller
 *
 */

public class Invoice {
	
	private static final AtomicLong counter = new AtomicLong(1);
	private long invoiceId;
	private Customer customer;
	private Collection<Item> items = new ArrayList<>();
	private float invoiceTotal;
	
	
	public Invoice() {
		this.invoiceId = counter.getAndIncrement();
	}

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Float getInvoiceTotal() {
		return invoiceTotal;
	}

	public void setInvoiceTotal(float invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}
	
	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		items.add(item);
		setItems(items);
	}

	public void calculateInvoiceTotal(float total) {
		invoiceTotal += total;
		setInvoiceTotal(invoiceTotal);
	}
}
