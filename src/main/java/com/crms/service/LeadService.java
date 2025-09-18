package com.crms.service;

import java.util.List;

import com.crms.dto.LeadDto;
import com.crms.entity.Customer;

public interface LeadService {
	List<LeadDto> getAllLeads();
    LeadDto getLeadById(Long id);
    LeadDto addLead(LeadDto dto);
    LeadDto updateLead(Long id, LeadDto dto);
    void deleteLead(Long id);
    public Customer convertLeadToCustomer(Long leadId, String company);
}
