package br.com.valentin.orangetalents.models;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "veiculo")
public class Veiculo{

	public Veiculo() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idveiculo;
	
	@NotBlank
	@Column(nullable = false)
	private String marca;
	
	@NotBlank
	@Column(nullable = false)
	private String modelo;
	
	@NotBlank
	@Column(length = 4, nullable = false)
	private String ano;
	
	private String valor;
	
	@Column(name = "dia_rodizio")
	private int diaRodizio;
	
	@Column(name = "rodizio_Ativo", columnDefinition="bit(1) default 0")
	private boolean rodizioAtivo;
	
	@Column(name = "dia_rodizio_rotulo")
	private String diaRodizioRotulo;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="pessoa_idpessoa")
	private Pessoa pessoa;
	
	
	// Getters e Setters

	public Long getIdveiculo() {
		return idveiculo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setDiaRodizio(int diaRodizio) {
		this.diaRodizio = diaRodizio;
	}

	public void setIdveiculo(Long idveiculo) {
		this.idveiculo = idveiculo;
	}

	public int getDiaRodizio() {
		return diaRodizio;
	}

	public void setDiaRodizio(String ano) {
		int dayRodizio = Character.getNumericValue(ano.charAt(ano.length()-1));

		switch (dayRodizio) {
			case 0:
			case 1:
				this.diaRodizio = 2;
				this.setDiaRodizioRotulo("Segunda-feira");
				break;
			case 2:
			case 3:
				this.diaRodizio = 3;
				this.setDiaRodizioRotulo("Terça-feira");
				break;
			case 4:
			case 5:
				this.diaRodizio = 4;
				this.setDiaRodizioRotulo("Quarta-feira");
				break;
			case 6:
			case 7:
				this.diaRodizio = 5;
				this.setDiaRodizioRotulo("Quinta-feira");
				break;
			case 8:
			case 9:
				this.diaRodizio = 6;
				this.setDiaRodizioRotulo("Sexta-feira");
				break;
			default:
				this.diaRodizio = 0;
				break;
		}
		
	}

	public boolean isRodizioAtivo() {
		Calendar calendar = Calendar.getInstance();
		if(calendar.get(Calendar.DAY_OF_WEEK) == this.getDiaRodizio()) {
			this.setRodizioAtivo(true);
			return true;
		}else {
			this.setRodizioAtivo(false);
			return false;
		}
	}

	private void setRodizioAtivo(boolean rodizioAtivo) {
		this.rodizioAtivo = rodizioAtivo;
	}
	
	public String getDiaRodizioRotulo() {
		return diaRodizioRotulo;
	}
	
	private void setDiaRodizioRotulo(String rotulo) {
		this.diaRodizioRotulo = rotulo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + diaRodizio;
		result = prime * result + ((diaRodizioRotulo == null) ? 0 : diaRodizioRotulo.hashCode());
		result = prime * result + ((idveiculo == null) ? 0 : idveiculo.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + (rodizioAtivo ? 1231 : 1237);
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (diaRodizio != other.diaRodizio)
			return false;
		if (diaRodizioRotulo == null) {
			if (other.diaRodizioRotulo != null)
				return false;
		} else if (!diaRodizioRotulo.equals(other.diaRodizioRotulo))
			return false;
		if (idveiculo == null) {
			if (other.idveiculo != null)
				return false;
		} else if (!idveiculo.equals(other.idveiculo))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (rodizioAtivo != other.rodizioAtivo)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}



}
