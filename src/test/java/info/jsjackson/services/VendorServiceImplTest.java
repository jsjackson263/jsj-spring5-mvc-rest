/**
 * 
 */
package info.jsjackson.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import info.jsjackson.api.v1.mapper.VendorMapper;
import info.jsjackson.api.v1.model.VendorDTO;
import info.jsjackson.domain.Vendor;
import info.jsjackson.repositories.VendorRepository;

/**
 * @author jsjackson
 *
 */
public class VendorServiceImplTest {

	public static final Long ID1 = 1L;
	public static final String NAME1 = "New Vendor";
	public static final String URL1 = "/api/v1/vendors/1";

	public static final Long ID3 = 3L;
	public static final String NAME3 = "Tutti Fruti";
	public static final String URL3 = "/api/v1/vendors/3";

	public static final Long ID5 = 5L;
	public static final String NAME5 = "Fruits R Us";
	public static final String URL5 = "/api/v1/vendors/5";

	@Mock
	VendorRepository vendorRepository;

	VendorMapper vendorMapper = VendorMapper.INSTANCE;

	VendorService vendorService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		vendorService = new VendorServiceImpl(vendorMapper, vendorRepository);

	}

	@Test
	public void testGetAllVendors() throws Exception {

		// Given
		List<Vendor> vendorList = new ArrayList<>();
		Vendor vendor3 = new Vendor();
		vendor3.setId(ID3);
		vendor3.setName(NAME3);
		vendorList.add(vendor3);

		Vendor vendor5 = new Vendor();
		vendor5.setId(ID5);
		vendor5.setName(NAME5);
		vendorList.add(vendor5);

		when(vendorRepository.findAll()).thenReturn(vendorList);

		// When
		List<VendorDTO> returnedVendorList = vendorService.getAllVendors();

		// Then
		assertNotNull(returnedVendorList);
		assertEquals(2, returnedVendorList.size());
		assertEquals(NAME5, returnedVendorList.get(1).getName());
		assertEquals(URL3, returnedVendorList.get(0).getVendorUrl());

		String vendorString = returnedVendorList.get(0).toString();
		assertTrue(vendorString.contains("Tutti"));

	}

	@Test
	public void testGetVendorById() throws Exception {

		// Given
		Vendor vendor = new Vendor();
		vendor.setId(ID3);
		vendor.setName(NAME3);

		Optional<Vendor> vendorOptional = Optional.of(vendor);
		when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);

		// When
		VendorDTO returnedVendorDTO = vendorService.getVendorById(ID3);

		// Then
		assertNotNull(returnedVendorDTO);
		assertEquals(ID3, returnedVendorDTO.getId());
		assertEquals(NAME3, returnedVendorDTO.getName());

	}

	@Test
	public void testGetVendorByName() throws Exception {

		// Given
		Vendor vendor = new Vendor();
		vendor.setId(ID5);
		vendor.setName(NAME5);

		when(vendorRepository.findByName(anyString())).thenReturn(vendor);

		// When
		VendorDTO returnedVendorDTO = vendorService.getVendorByName(NAME5);

		// Then
		assertNotNull(returnedVendorDTO);
		assertEquals(ID5, returnedVendorDTO.getId());
		assertEquals(NAME5, returnedVendorDTO.getName());

	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetVendorByNameNotFound() throws Exception {

		// Given
		when(vendorRepository.findByName(anyString())).thenReturn(null);

		// When
		VendorDTO returnedVendorDTO = vendorService.getVendorByName(NAME5);

		// Then
		// assertNotNull(returnedVendorDTO);
		// assertEquals(ID5, returnedVendorDTO.getId());
		// assertEquals(NAME5, returnedVendorDTO.getName());

	}

	@Test
	public void testCreateNewVendor() throws Exception {

		// Given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(ID1);
		vendorDTO.setName(NAME1);
		vendorDTO.setVendorUrl(URL1);

		Vendor savedVendor = new Vendor();
		savedVendor.setId(vendorDTO.getId());
		savedVendor.setName(vendorDTO.getName());

		when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

		// When
		VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

		// Then
		assertEquals(ID1, savedDTO.getId());
		assertEquals(NAME1, savedDTO.getName());
		assertEquals(URL1, savedDTO.getVendorUrl());

	}

	@Test
	public void testSaveVendor() throws Exception {

		// Given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(ID1);
		vendorDTO.setName(NAME1);
		vendorDTO.setVendorUrl(URL1);

		Vendor savedVendor = new Vendor();
		savedVendor.setId(vendorDTO.getId());
		savedVendor.setName(vendorDTO.getName());

		when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

		// When
		VendorDTO returnedDTO = vendorService.saveVendorByDTO(ID1, vendorDTO);

		// Then
		assertNotNull(returnedDTO);
		assertEquals(ID1, returnedDTO.getId());
		assertEquals(NAME1, returnedDTO.getName());

	}

	@Test
	public void testDeleteVendor() throws Exception {

		// Given
		Long idToDelete = ID1;
		
		// When
		vendorService.deleteVendor(idToDelete);
		
		
		// Then
		verify(vendorRepository, times(1)).deleteById(anyLong());

	}

}
