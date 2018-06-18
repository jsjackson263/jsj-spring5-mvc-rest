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
	
	CustomerDTO createNewCustomer(CustomerDTO customerDTO);
	
	CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
	
	CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
	
	void deleteCustomerById(Long id);
	
}
