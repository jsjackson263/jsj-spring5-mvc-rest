/**
 * 
 */
package info.jsjackson.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import info.jsjackson.api.v1.model.CategoryDTO;
import info.jsjackson.api.v1.model.CategoryListDTO;
import info.jsjackson.api.v1.model.CustomerDTO;
import info.jsjackson.api.v1.model.CustomerListDTO;
import info.jsjackson.services.CustomerService;

/**
 * @author jsjackson
 *
 */
@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	@GetMapping
	public ResponseEntity<CustomerListDTO> getAllCustomers() {
		
		List<CustomerDTO> customerList = customerService.getAllCustomers();
		CustomerListDTO customerListDTO  = new CustomerListDTO(customerList);
		
		return new ResponseEntity<CustomerListDTO>(customerListDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
		
		CustomerDTO customerDTO = customerService.getCustomerById(Long.valueOf(id));
		
		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("customer/{lastName}")
	public ResponseEntity<CustomerDTO> getCustomerByLastName(@PathVariable String lastName) {
		
		CustomerDTO customerDTO = customerService.getCustomerByLastName(lastName);
		
		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
		
	}
	
}
