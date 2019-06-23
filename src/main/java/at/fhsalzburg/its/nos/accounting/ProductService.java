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
public class ProductService {
	private Map<Long, Product> productMap = new HashMap<>();

	
	public synchronized Product create(Product p) {
		productMap.put(p.getProductId(), p);
		return p;
	}
	
	public synchronized Product update(Product p, long productId) {
		if (!productMap.containsKey(productId)) {
			throw new RuntimeException("Product-ID unknown...");
		}
		
		Product temp = productMap.get(productId);
		temp.setProductId(productId);
		temp.setName(p.getName());
		temp.setUnitPrice(p.getUnitPrice());	
		return temp;
	}

	public Map<Long, Product> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Long, Product> productMap) {
		this.productMap = productMap;
	}

	public synchronized Collection<Product> getAll() {
		return productMap.values();
	}

	public synchronized Product get(long productId) {
		if (!productMap.containsKey(productId)) {
			throw new RuntimeException("Product-ID unknown...");
		}
		return productMap.get(productId);
	}

	public synchronized Product delete(long productId) {
		if (!productMap.containsKey(productId)) {
			throw new RuntimeException("Product-ID unknown...");
		}

		Product temp = productMap.get(productId);
		productMap.remove(productId);
		return temp;
	}

}
