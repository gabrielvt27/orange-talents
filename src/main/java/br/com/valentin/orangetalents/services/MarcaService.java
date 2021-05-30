package br.com.valentin.orangetalents.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.valentin.orangetalents.models.Marca;

@FeignClient(url= "https://parallelum.com.br/fipe/api/v1/carros/marcas" , name = "marcas")
public interface MarcaService {
	
    @GetMapping
    List<Marca> buscaMarcas();
    
    @GetMapping("{codigomarca}/modelos")
    String buscaModelos(@PathVariable("codigomarca") int codigomarca);
    
    @GetMapping("{codigomarca}/modelos/{codigomodelo}/anos")
    String procuraAno(@PathVariable("codigomarca") int codigomarca, 
    		@PathVariable("codigomodelo") String codigomodelo);
    
    @GetMapping("{codigomarca}/modelos/{codigomodelo}/anos/{codigoano}")
    String buscaValor(@PathVariable("codigomarca") int codigomarca, 
    		@PathVariable("codigomodelo") String codigomodelo, 
    		@PathVariable("codigoano") String codigoano);
}
