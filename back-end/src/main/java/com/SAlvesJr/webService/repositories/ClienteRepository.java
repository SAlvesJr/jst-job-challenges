package com.SAlvesJr.webService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SAlvesJr.webService.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}