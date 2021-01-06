package com.orangetalents.orangetalentsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orangetalents.orangetalentsapi.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);
}
