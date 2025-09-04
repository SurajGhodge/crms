package com.crms.service.impl;

import com.crms.dto.LeadDto;
import com.crms.entity.Lead;
import com.crms.mapper.LeadMapper;
import com.crms.repository.LeadRepository;
import com.crms.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Override
    public List<LeadDto> getAllLeads() {
        return leadRepository.findAll()
                .stream()
                .map(LeadMapper::toDTO)
                .toList();
    }

    @Override
    public LeadDto getLeadById(Long id) {
        return leadRepository.findById(id)
                .map(LeadMapper::toDTO)
                .orElse(null);
    }

    @Override
    public LeadDto addLead(LeadDto dto) {
        Lead lead = LeadMapper.toEntity(dto);
        Lead saved = leadRepository.save(lead);
        return LeadMapper.toDTO(saved);
    }

    @Override
    public LeadDto updateLead(Long id, LeadDto dto) {
        return leadRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            existing.setPhone(dto.getPhone());
            existing.setStatus(dto.getStatus());
            Lead updated = leadRepository.save(existing);
            return LeadMapper.toDTO(updated);
        }).orElse(null);
    }

    @Override
    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }
}
