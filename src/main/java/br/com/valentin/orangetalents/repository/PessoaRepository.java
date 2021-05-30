package br.com.valentin.orangetalents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.valentin.orangetalents.models.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);
}


