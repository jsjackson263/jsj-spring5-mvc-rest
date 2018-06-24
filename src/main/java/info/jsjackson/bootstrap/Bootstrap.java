/**
 * 
 */
package info.jsjackson.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import info.jsjackson.domain.Category;
import info.jsjackson.domain.Customer;
import info.jsjackson.domain.Vendor;
import info.jsjackson.repositories.CategoryRepository;
import info.jsjackson.repositories.CustomerRepository;
import info.jsjackson.repositories.VendorRepository;

/**
 * @author jsjackson
 * 
 * CommandLineRunner is a SpringBoot specific interface
 *  - allows us to run application code on startup.
 *
 */
@Component
public class Bootstrap implements CommandLineRunner {
	
	private final CategoryRepository categoryRepository;
	private final CustomerRepository customerRepository;
	private final VendorRepository vendorRepository;

	
	/**
	 * @param categoryRepository
	 * @param customerRepository
	 */
	public Bootstrap(CategoryRepository categoryRepository, 
			CustomerRepository customerRepository,
			VendorRepository vendorRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.vendorRepository = vendorRepository;
	}


	@Override
	public void run(String... args) throws Exception {

		loadCategories();
		loadCustomers();
		loadVendors();
		
	}
	
	
	private void loadCategories() {
		
		Category fruits = new Category();
		fruits.setName("Fruits");
		
		Category dried = new Category();
		dried.setName("Dried");
		
		Category fresh = new Category();
		fresh.setName("Fresh");
		
		Category exotic = new Category();
		exotic.setName("Exotic");
		
		Category nuts = new Category();
		nuts.setName("Nuts");
		
		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);
		
		System.out.println("Categories Loaded = " + categoryRepository.count());
	}
	
	private void loadCustomers() {
		
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Lennon");
		customerRepository.save(customer);
		
		customer = new Customer();
		customer.setFirstName("Paul");
		customer.setLastName("Mcartney");
		customerRepository.save(customer);
		
		customer = new Customer();
		customer.setFirstName("George");
		customer.setLastName("Harrison");
		customerRepository.save(customer);
		
		customer = new Customer();
		customer.setFirstName("Ringo");
		customer.setLastName("Starr");
		customerRepository.save(customer);
		
		System.out.println("Customers Loaded = " + customerRepository.count());
	}
	
	private void loadVendors() {
		
		Vendor vendor1 = new Vendor();
		vendor1.setName("Vendor 1");
		vendorRepository.save(vendor1);
		
		Vendor vendor2 = new Vendor();
		vendor2.setName("Vendor 2");
		vendorRepository.save(vendor2);
		
		System.out.println("Vendors Loaded = " + vendorRepository.count());
	}
	
	

}
