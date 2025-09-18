package com.crms.mapper;

import com.crms.dto.LeadDto;
import com.crms.entity.Lead;

public class LeadMapper {

	public static LeadDto toDTO(Lead lead) {
		if (lead == null)
			return null;
		return new LeadDto(lead.getId(), lead.getName(), lead.getEmail(), lead.getPhone(), lead.getStatus(), lead.getCreatedBy());
	}

	public static Lead toEntity(LeadDto dto) {
		if (dto == null)
			return null;
		Lead lead = new Lead();
		lead.setId(dto.getId());
		lead.setName(dto.getName());
		lead.setEmail(dto.getEmail());
		lead.setPhone(dto.getPhone());
		lead.setStatus(dto.getStatus());
		lead.setCreatedBy(dto.getCreatedBy());
		return lead;
	}
}
