package com.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crms.entity.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long> {

}
