package at.fhsalzburg.its.nos.accounting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Sandra Priller
 *
 */

public class Product {

	private static final AtomicLong counter = new AtomicLong(1);
	private long productId;
    private String name;
    private float unitPrice;
    
    
    public Product() {
        this.productId = counter.getAndIncrement();
    }
 
	public long getProductId() {
		return productId;
	}
	
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	
}
