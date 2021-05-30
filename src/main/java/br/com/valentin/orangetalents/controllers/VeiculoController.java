package br.com.valentin.orangetalents.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.valentin.orangetalents.models.Marca;
import br.com.valentin.orangetalents.models.Pessoa;
import br.com.valentin.orangetalents.models.Veiculo;
import br.com.valentin.orangetalents.services.MarcaService;
import br.com.valentin.orangetalents.services.PessoaService;
import br.com.valentin.orangetalents.services.VeiculoService;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
	
	@Autowired
	VeiculoService serviceVeiculo;
	
	@Autowired
	PessoaService servicePessoa;
	
	@Autowired
    private MarcaService marcaService;
	
	@PostMapping("/{idpessoa}")
	public ResponseEntity<?> createVeiculo(@RequestBody @Valid Veiculo veiculo, 
			@PathVariable("idpessoa") Long idpessoa) 
			throws JsonMappingException, JsonProcessingException {
		Pessoa p = servicePessoa.findById(idpessoa);
		if(p == null) {
			return new ResponseEntity<String>("{\"erro\":\"Pessoa não encontrada\"}",HttpStatus.NOT_FOUND);
		}else {
			List<Marca> marcas = marcaService.buscaMarcas();
	    	
			Marca m = marcas.stream().filter(marca -> veiculo.getMarca()
						.toUpperCase().equals(marca.getNome().toUpperCase()))
						.findFirst().orElse(null);
			
			if(m == null) {
				return new ResponseEntity<String>("{\"erro\":\"Marca não encontrada\"}",HttpStatus.NOT_FOUND);
			}
			
			String modelos = marcaService.buscaModelos(m.getCodigo());
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(modelos);
			
			boolean flag = false;
			for (JsonNode n1 : jsonNode.get("modelos")) {
				if(n1.get("nome").asText().contains(veiculo.getModelo())) {
					String anos = marcaService.procuraAno(m.getCodigo(), n1.get("codigo").asText());
					JsonNode jsonAnos = objectMapper.readTree(anos);
					for(JsonNode n2 : jsonAnos) {
						if(n2.get("codigo").asText().contains(veiculo.getAno())) {
							String infos = marcaService.buscaValor(m.getCodigo(), 
									n1.get("codigo").asText(), 
									n2.get("codigo").asText());
							JsonNode jsonInfos = objectMapper.readTree(infos);
							veiculo.setValor(jsonInfos.get("Valor").asText());
							flag = true;
							break;
						}
					}
				}
				
				if(flag) {
					break;
				}
			}
	    	
			veiculo.setPessoa(p);
			return new ResponseEntity<Veiculo>(serviceVeiculo.create(veiculo),HttpStatus.CREATED);
		}
	}

}
