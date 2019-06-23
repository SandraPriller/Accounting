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


@RestController
public class CustomerController {

		@Resource
	    private CustomerService service;
		

	    @PostMapping("/customer")									//Create new Customer						
	    public Customer postCustomer(@RequestBody Customer g) {
	    	return service.create(g);
	    }
	    
	    @PutMapping(value="/customer/{customerId}")					//Change data from specific Customer
	    public Customer putCustomer(@PathVariable("customerId") long customerId, @RequestBody Customer g) {
	    	return service.update(g, customerId);
	    }
	    
	    @RequestMapping("/customers")								//Get all Customers and their data
	    public Collection<Customer> getAllCustomer() {
	    	return service.getAll();
	    }
	    
	    @RequestMapping(value="/customer/{customerId}")				//Get specific Customer 
	    public Customer customer(@PathVariable("customerId") long customerId) {
	    	return service.get(customerId);
	    }
	    
	    @DeleteMapping(value="/customer/{customerId}")
	    public Customer delete(@PathVariable("customerId") long customerId) {
	    	return service.delete(customerId);
	    }
	
}
