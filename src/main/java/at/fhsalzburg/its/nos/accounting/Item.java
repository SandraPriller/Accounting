package at.fhsalzburg.its.nos.accounting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Sandra Priller
 *
 */

public class Item {

	private static final AtomicLong counter = new AtomicLong(1);
	private long itemId;
	private Product product;
	private Integer quantity;
	private Float total;

	
	public Item() {
		this.itemId = counter.getAndIncrement();
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long invoiceId) {
		this.itemId = invoiceId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Float getTotal() {
		return total;
	}
	
	public void setTotal(Float total) {
		this.total = total;
	}
	
	public Float calculateTotal(Float total, int quantity) {
		total = product.getUnitPrice() * getQuantity();
		setTotal(total);
		return total;
	}	
}
