package at.fhsalzburg.its.nos.accounting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Sandra Priller
 *
 */

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InvoiceService {
	
	@Resource
	private CustomerService service;
	@Resource 
	private ProductService ps;
	
	private Map<Long, Invoice> invoiceMap = new HashMap<>();
	private Collection<Item> items = new ArrayList<>();
	
	
	public synchronized Invoice createInvoice(long customerId, Invoice i) {	
    	if(!service.getCustomerMap().containsKey(customerId)) {				
    		throw new RuntimeException("Customer does not exist...");
    		
    	} else {
    		Customer temp = service.get(customerId);
    		i.setCustomer(temp);
    		invoiceMap.put(i.getInvoiceId(), i);
    	}
    	return i;
	}
	
	public synchronized Item addInvoiceItem(long invoiceId, long productId, int quantity, Item item) {
		if (!ps.getProductMap().containsKey(productId)) {					
    		throw new RuntimeException("Product does not exist...");
    		
		} else if (!getInvoiceMap().containsKey(invoiceId)) {				
			throw new RuntimeException("Invoice does not exist...");
			
		} else if(quantity == 0) {
			throw new NumberFormatException("Quantity is zero...");
			
		} else {
			Invoice i = getInvoice(invoiceId);
			Product pro = ps.get(productId);
			item.setProduct(pro);
			item.setQuantity(quantity);
			float total = 0.2f;
			total = item.calculateTotal(total, quantity);
			i.addItem(item);
			i.calculateInvoiceTotal(total);
		}
		return item;		
	}
	
	public synchronized Invoice getInvoice(long invoiceId) {
		if (!invoiceMap.containsKey(invoiceId)) {
			throw new RuntimeException("Invoice-ID unknown...");
		}
		return invoiceMap.get(invoiceId);
	}
	
	public synchronized Collection<Invoice> getAll() {
		return invoiceMap.values();
	}
		
	public synchronized Item removeItem(long invoiceId, long itemId) {
		if (!invoiceMap.containsKey(invoiceId)) {
			throw new RuntimeException("Invoice-ID unknown...");
			
		}
			Invoice i = invoiceMap.get(invoiceId);
			items = i.getItems();
			Map<Long, Item> map = new HashMap<Long, Item>();
			
			for(Item item : items) {
				map.put(item.getItemId(), item);
			
			}
			
			Item it = map.get(itemId);
			map.remove(itemId);
			List<Item> list = new ArrayList<Item>(map.values());
			i.setItems(list);
			
			if(map.isEmpty()) {
				i.setInvoiceTotal(0);
				
			} else {
				i.setInvoiceTotal(i.getInvoiceTotal() - it.getTotal());
			}

		return it;
	}
	
	public synchronized Invoice deleteInvoice(long invoiceId) {
		if (!invoiceMap.containsKey(invoiceId)) {
			throw new RuntimeException("Invoice-ID unknown...");
		}

		Invoice temp = invoiceMap.get(invoiceId);
		invoiceMap.remove(invoiceId);
		return temp;
	}
	
	public Map<Long, Invoice> getInvoiceMap() {
		return invoiceMap;
	}

	public void setItemMap(Map<Long, Invoice> invoiceMap) {
		this.invoiceMap = invoiceMap;
	}
	
}
