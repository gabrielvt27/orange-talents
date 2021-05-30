package br.com.valentin.orangetalents.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.valentin.orangetalents.models.Pessoa;
import br.com.valentin.orangetalents.models.Veiculo;
import br.com.valentin.orangetalents.services.PessoaService;
import br.com.valentin.orangetalents.services.VeiculoService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaService servicePessoa;
	
	@Autowired
	VeiculoService serviceVeiculo;
	
	@PostMapping
	public ResponseEntity<?> createPessoa(@RequestBody @Valid Pessoa p) {
		
		if(servicePessoa.findByCpf(p.getCpf())) {
			return new ResponseEntity<String>("{\"erro\":\"CPF já existente\"}",HttpStatus.BAD_REQUEST);
		}
		
		if(servicePessoa.findByEmail(p.getEmail())) {
			return new ResponseEntity<String>("{\"erro\":\"E-mail já existente\"}",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Pessoa>(servicePessoa.create(p),HttpStatus.CREATED);
	}

	@GetMapping("/{idpessoa}")
	public ResponseEntity<?> listarVeiculos(@PathVariable("idpessoa") Long idpessoa) {
		Pessoa p = servicePessoa.findById(idpessoa);
		if(p == null) {
			return new ResponseEntity<String>("{\"erro\":\"Pessoa não encontrada\"}",HttpStatus.NOT_FOUND);
		}else {
			p.getVeiculos().forEach(veiculo -> {
				serviceVeiculo.updateIsRodizioAtivo(veiculo);
			});
			
			return new ResponseEntity<List<Veiculo>>(p.getVeiculos(),HttpStatus.OK);
		}
	}
	
}
