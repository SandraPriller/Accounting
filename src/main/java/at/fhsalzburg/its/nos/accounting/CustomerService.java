package at.fhsalzburg.its.nos.accounting;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Sandra Priller
 *
 */

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CustomerService {
	private Map<Long, Customer> customerMap = new HashMap<>();

	
	public synchronized Customer create(Customer g) { 		
		customerMap.put(g.getCustomerId(), g);
		return g;
	}

	public synchronized Customer update(Customer g, long customerId) {
		if (!customerMap.containsKey(customerId)) {
			throw new RuntimeException("Customer-ID unknown...");
		}
		
		Customer temp = customerMap.get(customerId);
		temp.setCustomerId(customerId);
		temp.setName(g.getName());
		temp.setStreet(g.getStreet());
		temp.setZip(g.getZip());
		temp.setCity(g.getCity());	
		return temp;
	}

	public synchronized Collection<Customer> getAll() {
		return customerMap.values();
	}

	public synchronized Customer get(long customerId) {
		if (!customerMap.containsKey(customerId)) {
			throw new RuntimeException("Customer-ID unknown...");
		}
		return customerMap.get(customerId);
	}

	public Map<Long, Customer> getCustomerMap() {
		return customerMap;
	}

	public void setCustomerMap(Map<Long, Customer> customerMap) {
		this.customerMap = customerMap;
	}

	public synchronized Customer delete(long customerId) {
		if (!customerMap.containsKey(customerId)) {
			throw new RuntimeException("Customer-ID unknown...");
		}

		Customer temp = customerMap.get(customerId);
		customerMap.remove(customerId);
		return temp;
	}

}
