package com.perpet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpet.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, String>{
	Optional<Company> findByEmail(String email);
}
