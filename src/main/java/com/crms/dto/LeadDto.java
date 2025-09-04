package com.crms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadDto {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String status;
}
