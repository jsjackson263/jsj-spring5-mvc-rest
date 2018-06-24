/**
 * 
 */
package info.jsjackson.services;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import info.jsjackson.api.v1.mapper.VendorMapper;
import info.jsjackson.api.v1.model.VendorDTO;
import info.jsjackson.api.v1.model.VendorListDTO;
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
		
		given(vendorRepository.findAll()).willReturn(vendorList);

		// When
		VendorListDTO vendorListDTO = vendorService.getAllVendors();

		// Then
		assertNotNull(vendorListDTO);
		//New type of JUnit assertion that uses Hamcrest matchers
		assertThat(vendorListDTO.getVendors().size(), is(equalTo(2)));
		assertThat(vendorListDTO.getVendors().get(1).getName(), is(equalTo(NAME5)));
		assertThat(vendorListDTO.getVendors().get(0).getVendorUrl(), is(equalTo(URL3)));

		String vendorString = vendorListDTO.getVendors().get(0).toString();
		assertTrue(vendorString.contains("Tutti"));

	}

	@Test
	public void testGetVendorById() throws Exception {

		// Given
		Vendor vendor = new Vendor();
		vendor.setId(ID3);
		vendor.setName(NAME3);

		given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

		// When
		VendorDTO returnedVendorDTO = vendorService.getVendorById(ID3);

		// Then
		assertNotNull(returnedVendorDTO);
		assertThat(returnedVendorDTO.getId(), is(equalTo(ID3)));
		assertThat(returnedVendorDTO.getName(), is(equalTo(NAME3)));

	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testGetVendorByIdNotFound() throws Exception {

		// Given
		Vendor vendor = new Vendor();
		vendor.setId(ID3);
		vendor.setName(NAME3);

		given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());

		// When
		VendorDTO returnedVendorDTO = vendorService.getVendorById(ID3);

		// Then
		assertNotNull(returnedVendorDTO);
		assertThat(returnedVendorDTO.getId(), is(equalTo(ID3)));
		assertThat(returnedVendorDTO.getName(), is(equalTo(NAME3)));

	}
	

	@Test
	public void testGetVendorByName() throws Exception {

		// Given
		Vendor vendor = new Vendor();
		vendor.setId(ID5);
		vendor.setName(NAME5);

		given(vendorRepository.findByName(anyString())).willReturn(vendor);

		// When
		VendorDTO returnedVendorDTO = vendorService.getVendorByName(NAME5);

		// Then
		assertNotNull(returnedVendorDTO);
		assertThat(returnedVendorDTO.getId(), is(equalTo(ID5)));
		assertThat(returnedVendorDTO.getName(), is(equalTo(NAME5)));

	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetVendorByNameNotFound() throws Exception {

		// Given
		given(vendorRepository.findByName(anyString())).willReturn(null);

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

		given(vendorRepository.save(any(Vendor.class))).willReturn(savedVendor);

		// When
		VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

		// Then
		assertThat(savedDTO.getId(), is(equalTo(ID1)));
		assertThat(savedDTO.getName(), is(equalTo(NAME1)));
		assertThat(savedDTO.getVendorUrl(), containsString("1"));

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

		given(vendorRepository.save(any(Vendor.class))).willReturn(savedVendor);

		// When
		VendorDTO returnedDTO = vendorService.saveVendorByDTO(ID1, vendorDTO);

		// Then
		assertNotNull(returnedDTO);
		assertThat(returnedDTO.getId(), is(equalTo(ID1)));
		assertThat(returnedDTO.getName(), is(equalTo(NAME1)));

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
