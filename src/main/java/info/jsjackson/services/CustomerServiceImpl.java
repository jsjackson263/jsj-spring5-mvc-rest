/**
 * 
 */
package info.jsjackson.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

	private CustomerMapper customerMapper;
	private CustomerRepository customerRepositpory;
	

	@Autowired
	public void setCustomerMapper(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}
	
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepositpory) {
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
		 customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
		
		return customerDTO;
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		
		return saveAndReturnDTO(customer);
		
	}
	
	private CustomerDTO saveAndReturnDTO(Customer customer) {
		
		Customer savedCustomer = customerRepositpory.save(customer);
		CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
		
		returnDTO.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
		
		return returnDTO;
		
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		customer.setId(id);
		
		return saveAndReturnDTO(customer);
		
	}


}
