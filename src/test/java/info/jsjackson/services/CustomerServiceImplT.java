/**
 * 
 */
package info.jsjackson.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import info.jsjackson.api.v1.mapper.CustomerMapper;
import info.jsjackson.api.v1.model.CustomerDTO;
import info.jsjackson.bootstrap.Bootstrap;
import info.jsjackson.domain.Customer;
import info.jsjackson.repositories.CategoryRepository;
import info.jsjackson.repositories.CustomerRepository;
import info.jsjackson.repositories.VendorRepository;

/**
 * @author jsjackson
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplT {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	CustomerServiceImpl customerService;
	
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Loading Customer Data");
		System.out.println(customerRepository.findAll().size());

		//setup data for testing
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
		bootstrap.run();  //load data
		
		customerService  = new CustomerServiceImpl(); 
		customerService.setCustomerMapper(CustomerMapper.INSTANCE);
		customerService.setCustomerRepository(customerRepository);
		
		
	}

	@Test
	public void testPatchCustomerUpdateFirstName() throws Exception {

		String updatedName = "UpdatedName";
		long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		
		//save original first/last name
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(updatedName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getFirstName());
		assertThat(originalFirstName,  not(equalTo(updatedCustomer.getFirstName())));
		assertThat(originalLastName,  equalTo(updatedCustomer.getLastName()));
		
		
	}
	
	@Test
	public void testPatchCustomerUpdateLastName() throws Exception {

		String updatedName = "UpdatedName";
		long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		
		//save original first/last name
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastName(updatedName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getLastName());
		assertThat(originalFirstName,  equalTo(updatedCustomer.getFirstName()));
		assertThat(originalLastName,  not(equalTo(updatedCustomer.getLastName())));
		
		
	}
	
	private long getCustomerIdValue() {
		
		List<Customer> customers = customerRepository.findAll();
		System.out.println("Customers Found: " + customers.size());
		
		//return the first id
		return customers.get(0).getId();
	}
	

}
