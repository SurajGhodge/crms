package com.crms.service.impl;

import com.crms.dto.CustomerDto;
import com.crms.entity.Customer;
import com.crms.mapper.CustomerMapper;
import com.crms.repository.CustomerRepository;
import com.crms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<CustomerDto> getAllCustomers() {
		return customerRepository.findAll().stream().map(CustomerMapper::toDTO).toList();
	}

	@Override
	public CustomerDto getCustomerById(Long id) {
		return customerRepository.findById(id).map(CustomerMapper::toDTO).orElse(null);
	}

	@Override
	public CustomerDto addCustomer(CustomerDto dto) {
		Customer customer = CustomerMapper.toEntity(dto);
		Customer saved = customerRepository.save(customer);
		return CustomerMapper.toDTO(saved);
	}

	@Override
	public CustomerDto updateCustomer(Long id, CustomerDto dto) {
		return customerRepository.findById(id).map(existing -> {
			existing.setName(dto.getName());
			existing.setEmail(dto.getEmail());
			existing.setPhone(dto.getPhone());
			existing.setCompany(dto.getCompany());
			Customer updated = customerRepository.save(existing);
			return CustomerMapper.toDTO(updated);
		}).orElse(null);
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
}
