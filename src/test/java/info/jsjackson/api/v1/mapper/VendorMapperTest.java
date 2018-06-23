/**
 * 
 */
package info.jsjackson.api.v1.mapper;

import static org.junit.Assert.*;

import org.junit.Test;

import info.jsjackson.api.v1.model.VendorDTO;
import info.jsjackson.domain.Vendor;

/**
 * @author jsjackson
 *
 */
public class VendorMapperTest {
	
	public static final Long ID = 1L;
	public static final String NAME = "Tutti Fruti";
	
	VendorMapper vendorMapper =  VendorMapper.INSTANCE;
	
	@Test
	public void testVendorToVendorDTO() {

		//Given
		Vendor vendor = new Vendor();
		vendor.setId(ID);
		vendor.setName(NAME);
		
		
		//When
		VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

		
		//Then
		assertNotNull(vendorDTO);
		assertEquals(Long.valueOf(1L), vendorDTO.getId());
		assertEquals(NAME, vendorDTO.getName());
		
	}

	//TODO: test VendorDTOToVendor conversion
}
