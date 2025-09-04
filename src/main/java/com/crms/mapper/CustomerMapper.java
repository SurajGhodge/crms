package com.crms.mapper;

import com.crms.dto.CustomerDto;
import com.crms.entity.Customer;

public class CustomerMapper {

	public static CustomerDto toDTO(Customer customer) {
		if (customer == null)
			return null;
		return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(),
				customer.getCompany());
	}

	public static Customer toEntity(CustomerDto dto) {
		if (dto == null)
			return null;
		Customer customer = new Customer();
		customer.setId(dto.getId());
		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());
		customer.setPhone(dto.getPhone());
		customer.setCompany(dto.getCompany());
		return customer;
	}
}
