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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author jsjackson
 *
 */
@Api(description = "This is the Vendor Controller")
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

	@ApiOperation(value = "This will get a list of vendors", notes = "Notes about the getAll operation")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorListDTO getAllVendors() {
		
		VendorListDTO  vendorListDTO = vendorService.getAllVendors();
		
		return vendorListDTO;
		
	}
	
	@ApiOperation(value = "This will get a vendor by id", notes = "Notes about the getVendorById operation")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorById(@PathVariable String id) {
		
		VendorDTO vendorDTO = vendorService.getVendorById(Long.valueOf(id));
		
		return vendorDTO;
	}
	
	@ApiOperation(value = "Gets a vendor by name")
	@GetMapping("/vendor/{name}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorByName(@PathVariable String name) {
		
		VendorDTO vendorDTO = vendorService.getVendorByName(name);
		
		return vendorDTO;
		
	}
	
	@ApiOperation(value = "Creates a new vendor")
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
		
		VendorDTO returnedDTO = vendorService.createNewVendor(vendorDTO);
		
		return returnedDTO;
	}
	
	@ApiOperation(value = "Updates a vendor by id")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO) {
		
		VendorDTO returnedDTO = vendorService.saveVendorByDTO(Long.valueOf(id), vendorDTO);
		
		return returnedDTO;
		
	}
	
	@ApiOperation(value = "Patches a vendor by id")
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO patchVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO) {
		
		VendorDTO returnedDTO = vendorService.patchVendor(Long.valueOf(id), vendorDTO);
		
		return returnedDTO;
	}
	
	@ApiOperation(value = "Deletes a vendor by id")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteVendorById(@PathVariable String id) {
		
		vendorService.deleteVendor(Long.valueOf(id));
		
	}
}
