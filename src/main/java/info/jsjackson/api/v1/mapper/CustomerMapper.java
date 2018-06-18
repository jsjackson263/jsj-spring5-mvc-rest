/**
 * 
 */
package info.jsjackson.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import info.jsjackson.api.v1.model.CustomerDTO;
import info.jsjackson.domain.Customer;

/**
 * @author jsjackson
 *
 */
@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	CustomerDTO customerToCustomerDTO(Customer customer); 
	
	Customer customerDtoToCustomer(CustomerDTO customerDTO);
	
}
