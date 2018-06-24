/**
 * 
 */
package info.jsjackson.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import info.jsjackson.api.v1.model.VendorDTO;
import info.jsjackson.api.v1.model.VendorListDTO;
import info.jsjackson.services.VendorService;

/**
 * @author jsjackson
 *
 */
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

	public static final String BASE_URL = "/api/v1/vendors";
	
	private final VendorService vendorService;
	
	/**
	 * @param vendorService
	 */
	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorListDTO getAllVendors() {
		
		VendorListDTO  vendorListDTO = vendorService.getAllVendors();
		
		return vendorListDTO;
		
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorById(@PathVariable String id) {
		
		VendorDTO vendorDTO = vendorService.getVendorById(Long.valueOf(id));
		
		return vendorDTO;
	}
	
	@GetMapping("/vendor/{name}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorByName(@PathVariable String name) {
		
		VendorDTO vendorDTO = vendorService.getVendorByName(name);
		
		return vendorDTO;
		
	}
	
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
		
		VendorDTO returnedDTO = vendorService.createNewVendor(vendorDTO);
		
		return returnedDTO;
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO) {
		
		VendorDTO returnedDTO = vendorService.saveVendorByDTO(Long.valueOf(id), vendorDTO);
		
		return returnedDTO;
		
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO patchVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO) {
		
		VendorDTO returnedDTO = vendorService.patchVendor(Long.valueOf(id), vendorDTO);
		
		return returnedDTO;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteVendorById(@PathVariable String id) {
		
		vendorService.deleteVendor(Long.valueOf(id));
		
	}
}
