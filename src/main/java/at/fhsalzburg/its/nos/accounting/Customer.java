package at.fhsalzburg.its.nos.accounting;

import java.util.concurrent.atomic.AtomicLong;


public class Customer {

	private static final AtomicLong counter = new AtomicLong(1);	
	private long customerId;
    private String name;
    private String street;
	private String zip;
    private String city;

    
    public Customer() {
        this.customerId = counter.getAndIncrement();
    }

    public long getCustomerId() {
        return customerId;
    }

	public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
	public String getStreet() {
		return street;
	}

	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
    }

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
    }

}
