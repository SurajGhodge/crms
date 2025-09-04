package com.crms.service;

import java.util.List;

import com.crms.dto.LeadDto;

public interface LeadService {
	List<LeadDto> getAllLeads();
    LeadDto getLeadById(Long id);
    LeadDto addLead(LeadDto dto);
    LeadDto updateLead(Long id, LeadDto dto);
    void deleteLead(Long id);
}
