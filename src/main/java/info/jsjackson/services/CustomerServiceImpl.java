/**
 * 
 */
package info.jsjackson.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import info.jsjackson.api.v1.mapper.CustomerMapper;
import info.jsjackson.api.v1.model.CustomerDTO;
import info.jsjackson.domain.Customer;
import info.jsjackson.repositories.CustomerRepository;

/**
 * @author jsjackson
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	CustomerMapper customerMapper;
	CustomerRepository customerRepositpory;
	
	
	/**
	 * @param customerMapper
	 * @param customerRepositpory
	 */
	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepositpory) {
		this.customerMapper = customerMapper;
		this.customerRepositpory = customerRepositpory;
	}

	
	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepositpory.findAll()
				.stream()
				.map(customer -> {
					CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
					customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
					return customerDTO;
				})
				.collect(Collectors.toList());
		
		
	}
	
	@Override
	public CustomerDTO getCustomerById(Long id) {
		
		//TODO: setCustomerUrl ???
		return customerRepositpory.findById(id)
				.map(customerMapper::customerToCustomerDTO)
				.orElseThrow(RuntimeException::new); //TODO: implement better exception handling
		
	}

	@Override
	public CustomerDTO getCustomerByLastName(String lastName) {
		
		Customer customer = customerRepositpory.findByLastName(lastName);
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
		
		return customerDTO;
	}


}
