package at.fhsalzburg.its.nos.accounting;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sandra Priller
 *
 */

@RestController
public class ProductController {

	@Resource
    private ProductService service;

    @PostMapping("/product")									//Create new Product						
    public Product postProduct(@RequestBody Product p) {
    	return service.create(p);
    }
    
    @PutMapping(value="/product/{productId}")					//Change data from specific Product
    public Product putProduct(@PathVariable("productId") long productId, @RequestBody Product p) {
    	return service.update(p, productId);
    }
    
    @RequestMapping("/products")								//Get all Products and their data
    public Collection<Product> getAllProducts() {
    	return service.getAll();
    }
    
    @RequestMapping(value="/product/{productId}")				//Get specific Product
    public Product product(@PathVariable("productId") long productId) {
    	return service.get(productId);
    }
    
    @DeleteMapping("/product/{productId}")
    public Product delete(@PathVariable("productId") long productId) {
    	return service.delete(productId);
    }
	
}
