package at.fhsalzburg.its.nos.accounting;

import java.util.Collection;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sandra Priller
 *
 */

@RestController
public class InvoiceController {
	
	@Resource
    private InvoiceService is;
	
	
    @RequestMapping(value="/customer/{customerId}/invoice")			//Create new Invoice						
    public Invoice postInvoice(@PathVariable("customerId") long customerId, @RequestBody Invoice i) {
    	return is.createInvoice(customerId, i);
    }
    
    @RequestMapping(value="/invoice/{invoiceId}/product/{productId}/item/quantity={quantity}")
    public Item postItem(@PathVariable("invoiceId") long invoiceId, @PathVariable("productId") long productId, @PathVariable("quantity") 
    	int quantity, @RequestBody Item item) {
			return is.addInvoiceItem(invoiceId, productId, quantity, item);   	
    }
    
    @RequestMapping("/invoices")									//Get all Invoices and their data
    public Collection<Invoice> getAllInvoices() {
    	return is.getAll();
    }
    
    @RequestMapping(value="/invoice/{invoiceId}")					//Get specific Invoice 
    public Invoice getSpecificInvoice(@PathVariable("invoiceId") long invoiceId) {
    	return is.getInvoice(invoiceId);
    }
   
    @DeleteMapping(value="/invoice/{invoiceId}/item/{itemId}") 		//Delete specific Item
    public Item remItem(@PathVariable("invoiceId") long invoiceId, @PathVariable("itemId") long itemId) {
    	return is.removeItem(invoiceId, itemId);
    }
    
    @DeleteMapping(value="/invoice/{invoiceId}")					//Delete specific Invoice
    public Invoice delete(@PathVariable("invoiceId") long invoiceId) {
    	return is.deleteInvoice(invoiceId);
    }
	
}
