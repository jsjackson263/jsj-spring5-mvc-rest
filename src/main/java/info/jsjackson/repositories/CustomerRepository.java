/**
 * 
 */
package info.jsjackson.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import info.jsjackson.domain.Customer;

/**
 * @author jsjackson
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByLastName(String lastName);
	
	Customer findByFirstName(String firstName);

	
}
