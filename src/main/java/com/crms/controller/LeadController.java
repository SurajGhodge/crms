package com.crms.controller;

import com.crms.dto.LeadDto;
import com.crms.service.impl.LeadServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

	@Autowired
	private  LeadServiceImpl leadService;

	@GetMapping
	public List<LeadDto> getAllLeads() {
		return leadService.getAllLeads();
	}

	@GetMapping("/{id}")
	public LeadDto getLeadById(@PathVariable Long id) {
		return leadService.getLeadById(id);
	}

	@PostMapping
	public LeadDto addLead(@RequestBody LeadDto dto) {
		return leadService.addLead(dto);
	}

	@PutMapping("/{id}")
	public LeadDto updateLead(@PathVariable Long id, @RequestBody LeadDto dto) {
		return leadService.updateLead(id, dto);
	}

	@DeleteMapping("/{id}")
	public void deleteLead(@PathVariable Long id) {
		leadService.deleteLead(id);
	}
}
