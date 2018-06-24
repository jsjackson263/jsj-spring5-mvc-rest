/**
 * 
 */
package info.jsjackson.services;

import java.util.List;

import info.jsjackson.api.v1.model.VendorDTO;
import info.jsjackson.api.v1.model.VendorListDTO;

/**
 * @author jsjackson
 *
 */
public interface VendorService {

	VendorListDTO getAllVendors();
	
	VendorDTO getVendorById(Long id);
	
	VendorDTO getVendorByName(String name);
	
	VendorDTO createNewVendor(VendorDTO vendorDTO);
	
	VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

	VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
	
	void deleteVendor(Long id);
	
}
