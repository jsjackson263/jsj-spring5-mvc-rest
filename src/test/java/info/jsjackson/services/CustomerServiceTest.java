/**
 * 
 */
package info.jsjackson.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import info.jsjackson.api.v1.mapper.CustomerMapper;
import info.jsjackson.api.v1.model.CustomerDTO;
import info.jsjackson.controllers.v1.CustomerController;
import info.jsjackson.domain.Customer;
import info.jsjackson.repositories.CustomerRepository;

/**
 * @author jsjackson
 *
 */
public class CustomerServiceTest {

	public static final long ID1 = 1L;
	public static final String FIRST_NAME1 = "Joe";
	public static final String LAST_NAME1 = "Blog";
	
	public static final long ID2 = 2L;
	public static final String FIRST_NAME2 = "Jane";
	public static final String LAST_NAME2 = "Doe";
	
	public static final long ID3 = 3L;
	public static final String FIRST_NAME3 = "Steve";
	public static final String LAST_NAME3 = "Hawking";
	
	
	CustomerServiceImpl customerService;
	
	
	@Mock
	CustomerRepository customerRepository;
	
	CustomerMapper customerMapper = CustomerMapper.INSTANCE;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		customerService = new CustomerServiceImpl();
		customerService.setCustomerMapper(customerMapper);
		customerService.setCustomerRepository(customerRepository);
		
	}

	
	@Test
	public void testGetAllCustomers() throws Exception {

		//Given 
		List<Customer> customerList = new ArrayList<>();
		
		Customer customer1 = new Customer();
		customer1.setId(ID1);
		customer1.setFirstName(FIRST_NAME1);
		customer1.setLastName(LAST_NAME1);
		customerList.add(customer1);
		
		Customer customer2 = new Customer();
		customer2.setId(ID2);
		customer2.setFirstName(FIRST_NAME2);
		customer2.setLastName(LAST_NAME2);
		customerList.add(customer2);

		when(customerRepository.findAll()).thenReturn(customerList);
		
		//When
		List<CustomerDTO> customerDTOs = customerService.getAllCustomers();
		
		//Then
		assertEquals(2, customerDTOs.size());
		
	}

	@Test
	public void testGetCustomerById() throws Exception {
		
		//Given
		Customer customer = new Customer();
		customer.setId(ID1);
		customer.setFirstName(FIRST_NAME1);
		customer.setLastName(LAST_NAME1);
		
		when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));
		
		//When
		CustomerDTO customerDTO = customerService.getCustomerById(ID1);
		
		//Then
		assertEquals(Long.valueOf(ID1), customerDTO.getId());
		assertEquals(FIRST_NAME1, customerDTO.getFirstName());
		assertEquals(LAST_NAME1, customerDTO.getLastName());
		
	}
	
	
	@Test
	public void testGetCustomerByLastName() throws Exception {
		
		//Given
		Customer customer = new Customer();
		customer.setId(ID1);
		customer.setFirstName(FIRST_NAME1);
		customer.setLastName(LAST_NAME1);
		
		when(customerRepository.findByLastName(anyString())).thenReturn(customer);
		
		//When
		CustomerDTO customerDTO = customerService.getCustomerByLastName(LAST_NAME1);
		
		//Then
		assertEquals(Long.valueOf(ID1), customerDTO.getId());
		assertEquals(FIRST_NAME1, customerDTO.getFirstName());
		assertEquals(LAST_NAME1, customerDTO.getLastName());
		
	}
	
	@Test
	public void testCreateNewCustomer() throws Exception {
		
		//Given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(ID3);
		customerDTO.setFirstName(FIRST_NAME3);
		customerDTO.setLastName(LAST_NAME3);
		
		Customer savedCustomer = new Customer();
		savedCustomer.setId(customerDTO.getId());
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		
		
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		//When
		CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);
		
		//Then
		assertEquals(Long.valueOf(ID3), savedDTO.getId());
		assertEquals(FIRST_NAME3, savedDTO.getFirstName());
		assertEquals(LAST_NAME3, savedDTO.getLastName());
		assertEquals(CustomerController.BASE_URL + "/3", savedDTO.getCustomerUrl());
		
	}

	@Test
	public void testSaveCustomerByDTO() throws Exception {
		
		//Given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(ID3);
		customerDTO.setFirstName(FIRST_NAME3);
		customerDTO.setLastName(LAST_NAME3);
		
		Customer savedCustomer = new Customer();
		savedCustomer.setId(customerDTO.getId());
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		
		
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		//When
		CustomerDTO savedDTO = customerService.saveCustomerByDTO(ID3, customerDTO);
		
		//Then
		assertEquals(Long.valueOf(ID3), savedDTO.getId());
		assertEquals(FIRST_NAME3, savedDTO.getFirstName());
		assertEquals(LAST_NAME3, savedDTO.getLastName());
		assertEquals(CustomerController.BASE_URL + "/3", savedDTO.getCustomerUrl());
		
	}
	
	@Test
	public void testDeleteCustomerById() throws Exception {
		
		Long idToDelete = Long.valueOf(1L);
		
		//When
		customerRepository.deleteById(idToDelete);
		
		//Then
		verify(customerRepository, times(1)).deleteById(anyLong());
		
	}

	
}
