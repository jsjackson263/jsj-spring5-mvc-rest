/**
 * 
 */
package info.jsjackson.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import info.jsjackson.api.v1.model.CustomerDTO;
import info.jsjackson.api.v1.model.CustomerListDTO;
import info.jsjackson.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author jsjackson
 * 
 * @Controller annotation - the old way of doing it
 *
 */
@Api(description = "This is my Customer Controller")
@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public static final String BASE_URL = "/api/v1/customers";
	
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	@ApiOperation(value = "This will get a list of customers", notes = "These are some notes about the Api")
	@GetMapping
	public ResponseEntity<CustomerListDTO> getAllCustomers() {
		
		List<CustomerDTO> customerList = customerService.getAllCustomers();
		CustomerListDTO customerListDTO  = new CustomerListDTO(customerList);
		
		return new ResponseEntity<CustomerListDTO>(customerListDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
		
		CustomerDTO customerDTO = customerService.getCustomerById(Long.valueOf(id));
		
		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
		
	}
	
	@GetMapping("/customer/{lastName}")
	public ResponseEntity<CustomerDTO> getCustomerByLastName(@PathVariable String lastName) {
		
		CustomerDTO customerDTO = customerService.getCustomerByLastName(lastName);
		
		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
		/* @RequestBody:
		 * - causes the object to  be bound automatically
		 * - tells SpringMVC to look at the body of the request, parse it, & create a DTO out of it */
		
		CustomerDTO retunDTO = customerService.createNewCustomer(customerDTO);
		
		return new ResponseEntity<CustomerDTO>(retunDTO, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
		
		CustomerDTO retunDTO = customerService.saveCustomerByDTO(Long.valueOf(id), customerDTO);
		
		return new ResponseEntity<CustomerDTO>(retunDTO, HttpStatus.OK);
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
		
		CustomerDTO retunDTO = customerService.patchCustomer(Long.valueOf(id), customerDTO);
		
		return new ResponseEntity<CustomerDTO>(retunDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomerById(@PathVariable String id) {
		
		customerService.deleteCustomerById(Long.valueOf(id));
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
}
