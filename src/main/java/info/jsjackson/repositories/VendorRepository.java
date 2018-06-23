/**
 * 
 */
package info.jsjackson.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import info.jsjackson.domain.Vendor;

/**
 * @author jsjackson
 *
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {

	Vendor findByName(String name) ;
	
}
