package erp.negocio.veiculo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import erp.negocio.centrocusto.CentroCusto;
import erp.negocio.veiculo.marca.VeiculoMarca;
import erp.negocio.veiculo.modelo.VeiculoModelo;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(indexes = { @Index(name = "INDEX_VEICULO_PLACA", columnList = "placa", unique = true),
		@Index(name = "INDEX_VEICULO_RENAVAM", columnList = "renavam", unique = true),
		@Index(name = "INDEX_VEICULO_CHASSI", columnList = "chassi", unique = true) })
public class Veiculo implements Serializable {
	
	@Column(length = 4)
	private String anoFabricacao;
	@Column(length = 4)
	private String anoModelo;
	@Column(length = 4)
	private String anoReferenciaCompra;
	@Column(length = 4)
	private String anoReferenciaVenda;
	@ManyToOne(cascade = CascadeType.ALL)
	private CentroCusto centroCusto;
	@Column(length = 20)
	private String chassi;
	@Column(length = 10)
	private String dataCompra;
	@Column(length = 10)
	private String dataVenda;
	@Column(length = 9)
	private String fabricacao;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	private VeiculoMarca marca;
	@Column(length = 9)
	private String mesReferenciaCadastro;
	@Column(length = 9)
	private String mesReferenciaCompra;
	@Column(length = 9)
	private String mesReferenciaVenda;
	@ManyToOne(cascade = CascadeType.ALL)
	private VeiculoModelo modelo;
	@Column(length = 9)
	private String numeroMotor;
	@Column(length = 8, nullable = false)
	private String placa;
	@Column(length = 50)
	private String proprietarioNome;
	@Column(length = 15)
	private String renavam;
	@Column(length = 20)
	private String restricoes;
	@Column(length = 50)
	private String rodas;
	@Column(length = 50)
	private String subCategoria;
	@Column(length = 17)
	private String tipo;
	private double valorCompra;
	private double valorVenda;
	@Column(length = 1)
	private String valvulas;
	@Column(length = 3)
	private String zeroKm;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public String getAnoModelo() {
		return anoModelo;
	}

	public String getAnoReferenciaCompra() {
		return anoReferenciaCompra;
	}

	public String getAnoReferenciaVenda() {
		return anoReferenciaVenda;
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public String getChassi() {
		return chassi;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public String getFabricacao() {
		return fabricacao;
	}

	public Long getId() {
		return id;
	}


	public VeiculoMarca getMarca() {
		return marca;
	}

	public String getMesReferenciaCadastro() {
		return mesReferenciaCadastro;
	}

	public String getMesReferenciaCompra() {
		return mesReferenciaCompra;
	}

	public String getMesReferenciaVenda() {
		return mesReferenciaVenda;
	}

	public VeiculoModelo getModelo() {
		return modelo;
	}

	public String getNumeroMotor() {
		return numeroMotor;
	}

	public String getPlaca() {
		return placa;
	}

	public String getProprietarioNome() {
		return proprietarioNome;
	}

	public String getRenavam() {
		return renavam;
	}

	public String getRestricoes() {
		return restricoes;
	}

	public String getRodas() {
		return rodas;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public String getTipo() {
		return tipo;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public String getValvulas() {
		return valvulas;
	}

	public String getZeroKm() {
		return zeroKm;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}

	public void setAnoReferenciaCompra(String anoReferenciaCompra) {
		this.anoReferenciaCompra = anoReferenciaCompra;
	}

	public void setAnoReferenciaVenda(String anoReferenciaVenda) {
		this.anoReferenciaVenda = anoReferenciaVenda;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public void setFabricacao(String fabricacao) {
		this.fabricacao = fabricacao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMarca(VeiculoMarca marca) {
		this.marca = marca;
	}

	public void setMesReferenciaCadastro(String mesReferenciaCadastro) {
		this.mesReferenciaCadastro = mesReferenciaCadastro;
	}

	public void setMesReferenciaCompra(String mesReferenciaCompra) {
		this.mesReferenciaCompra = mesReferenciaCompra;
	}

	public void setMesReferenciaVenda(String mesReferenciaVenda) {
		this.mesReferenciaVenda = mesReferenciaVenda;
	}

	public void setModelo(VeiculoModelo modelo) {
		this.modelo = modelo;
	}

	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = numeroMotor;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setProprietarioNome(String proprietarioNome) {
		this.proprietarioNome = proprietarioNome;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public void setRestricoes(String restricoes) {
		this.restricoes = restricoes;
	}

	public void setRodas(String rodas) {
		this.rodas = rodas;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public void setValvulas(String valvulas) {
		this.valvulas = valvulas;
	}

	public void setZeroKm(String zeroKm) {
		this.zeroKm = zeroKm;
	}

	@Override
	public String toString() {
		return placa;
	}

}
