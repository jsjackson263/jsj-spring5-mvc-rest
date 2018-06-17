/**
 * 
 */
package info.jsjackson.services;

import java.util.List;

import info.jsjackson.api.v1.model.CustomerDTO;

/**
 * @author jsjackson
 *
 */
public interface CustomerService {

	List<CustomerDTO> getAllCustomers();
	
	CustomerDTO getCustomerById(Long id);
	
	CustomerDTO getCustomerByLastName(String lastName);
	
	
}
