/**
 * 
 */
package info.jsjackson.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import info.jsjackson.api.v1.mapper.VendorMapper;
import info.jsjackson.api.v1.model.VendorDTO;
import info.jsjackson.api.v1.model.VendorListDTO;
import info.jsjackson.domain.Vendor;
import info.jsjackson.repositories.VendorRepository;

/**
 * @author jsjackson
 *
 */
@Service
public class VendorServiceImpl implements VendorService {
	
	private final VendorMapper vendorMapper;
	private final VendorRepository vendorRepository;


	/**
	 * @param vendorMapper
	 * @param vendorRepository
	 */
	public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
		this.vendorMapper = vendorMapper;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public VendorListDTO getAllVendors() {
		
		List<VendorDTO> vendorDTOs = 
			vendorRepository.findAll()
				.stream()
				.map(vendor -> {
					VendorDTO vendoDTO = vendorMapper.vendorToVendorDTO(vendor);
					vendoDTO.setVendorUrl(getVendorUrl(vendor.getId()));
					
					return vendoDTO;
				})
				.collect(Collectors.toList());
		
		return new VendorListDTO(vendorDTOs);
	}
	

	@Override
	public VendorDTO getVendorById(Long id) {
		return vendorRepository.findById(id)
				.map(vendorMapper::vendorToVendorDTO)
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public VendorDTO getVendorByName(String name) {
		
		Vendor vendor = vendorRepository.findByName(name);
		
		if (vendor != null) {
			VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
			vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
			return vendorDTO;
		} else {
			throw new ResourceNotFoundException("No vendor found with name " + name);
		}
		
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) {

		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO); 
		return saveAndReturnDTO(vendor) ;
		
	}

	
	@Override
	public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO); 
		vendor.setId(id);
		
		return saveAndReturnDTO(vendor);
		
	}

	//TODO:Refactor this method for use by all classes that need it
	private VendorDTO saveAndReturnDTO(Vendor vendor) {
			
		Vendor savedVendor = vendorRepository.save(vendor);
		VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);
			
		returnDTO.setVendorUrl(getVendorUrl(vendor.getId()));
			
		return returnDTO;
			
	}
		
		
	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
		
		return vendorRepository.findById(id)
				.map(vendor -> {
					//TODO:if more properties, add more if statements
					if (vendorDTO.getName() != null) {
						vendor.setName(vendorDTO.getName());
					}
					
					Vendor savedVendor = vendorRepository.save(vendor);
					VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);
					returnDTO.setVendorUrl(getVendorUrl(id));
					
					return returnDTO;
					
				})
				.orElseThrow(ResourceNotFoundException::new);
	}

	
	@Override
	public void deleteVendor(Long id) {
		vendorRepository.deleteById(id);
		
	}

	private String getVendorUrl(Long id) {
		return "/api/v1/vendors" + "/" + id;
	}
	
	
}
