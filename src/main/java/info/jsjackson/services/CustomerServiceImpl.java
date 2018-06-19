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
import info.jsjackson.controllers.v1.CustomerController;
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
					customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
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
		 customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
		
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
		
		returnDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));
		
		return returnDTO;
		
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		customer.setId(id);
		
		return saveAndReturnDTO(customer);
		
	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {

		return customerRepositpory.findById(id)
				.map(customer -> {
					if (customerDTO.getFirstName() != null) {
						customer.setFirstName(customerDTO.getFirstName());
					}
					if (customerDTO.getLastName() != null) {
						customer.setLastName(customerDTO.getLastName());
					}
					
				Customer savedCustomer = customerRepositpory.save(customer);
				
				CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(savedCustomer);
				returnedDTO.setCustomerUrl(getCustomerUrl(id));
				
				return returnedDTO;
					
				}).orElseThrow(RuntimeException::new); //TODO: Implement better exception handling
	
	}

	
	private String getCustomerUrl(Long id) {
		return CustomerController.BASE_URL + "/" + id;
	}
	
	
	@Override
	public void deleteCustomerById(Long id) {
		/* needs error-handling: either
		 * 1. check if exists, then delete, or
		 * 2. just delete. if it's not there, return some code */
		customerRepositpory.deleteById(id);  
		
	}


}
