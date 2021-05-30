package br.com.valentin.orangetalents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.valentin.orangetalents.models.Pessoa;
import br.com.valentin.orangetalents.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository repository;
	
	public Pessoa findById(Long idpessoa) {
		return repository.findById(idpessoa).orElse(null);
	}
	
	public boolean findByEmail(String email) {
		return repository.existsByEmail(email);
	}
	
	public boolean findByCpf(String cpf) {
		return repository.existsByCpf(cpf);
	}
	
	public Pessoa create(Pessoa pessoa) {
		return repository.save(pessoa);
	}
}


