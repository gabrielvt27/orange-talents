package br.com.valentin.orangetalents.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.valentin.orangetalents.models.Veiculo;
import br.com.valentin.orangetalents.repository.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	VeiculoRepository repository;
	
	public List<Veiculo> findAll(){
		return repository.findAll();
	}
	
	public void updateIsRodizioAtivo (Veiculo v) {
		v.isRodizioAtivo();
		repository.save(v);
	}
	
	public Veiculo create(Veiculo veiculo) {
		veiculo.setDiaRodizio(veiculo.getAno());
		veiculo.isRodizioAtivo();
		
		return repository.save(veiculo);
	}
}
