/**
 * 
 */
package info.jsjackson.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import info.jsjackson.api.v1.model.VendorDTO;
import info.jsjackson.domain.Vendor;

/**
 * @author jsjackson
 *
 */
@Mapper
public interface VendorMapper {
	
	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
	
	VendorDTO vendorToVendorDTO(Vendor vendor);
	
	Vendor vendorDTOToVendor(VendorDTO vendorDTO);
	
	

}
