package com.orangetalents.orangetalentsapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.orangetalents.orangetalentsapi.model.Pessoa;
import com.orangetalents.orangetalentsapi.repository.PessoaRepository;

@RestController
@RequestMapping("/cadastrar")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepo;
	
	@PostMapping
	public ResponseEntity<Pessoa> criarConta(@RequestBody @Valid Pessoa p) {
		if(pessoaRepo.existsByCpf(p.getCpf())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"O CPF já existe na base de dados");
		}
		
		if(pessoaRepo.existsByEmail(p.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"O E-mail já existe na base de dados");
		}
		
		return new ResponseEntity<Pessoa>(pessoaRepo.save(p), HttpStatus.CREATED);
	}
	
}

