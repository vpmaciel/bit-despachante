package erp.negocio.veiculo.documento;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;

import erp.negocio.veiculo.Veiculo;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
public class VeiculoDocumento implements Serializable {
	@Column(length = 19)
	private String cnpjRecebedorVeiculoDocumento;
	@Column(length = 14)
	private String cpfRecebedorVeiculoDocumento;
	@Column(length = 3)
	private String dataDevolucaoVeiculoDocumento;
	@Column(length = 2)
	private String dataRecebimentoVeiculoDocumento;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 15)
	private String localVeiculoDocumento;
	@Column(length = 50)
	private String nomeRecebedorVeiculoDocumento;
	@Column(length = 15)
	private String rgNumeroRecebedorVeiculoDocumento;
	@Column(length = 15)
	private String rgOrgaoEmisssorRecebedorVeiculoDocumento;
	@Column(length = 15)
	private String situacaoVeiculoDocumento;
	@ManyToOne(cascade = CascadeType.ALL)
	private Veiculo veiculo;

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
		VeiculoDocumento other = (VeiculoDocumento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCnpjRecebedorVeiculoDocumento() {
		return cnpjRecebedorVeiculoDocumento;
	}

	public String getCpfRecebedorVeiculoDocumento() {
		return cpfRecebedorVeiculoDocumento;
	}

	public String getDataDevolucaoVeiculoDocumento() {
		return dataDevolucaoVeiculoDocumento;
	}

	public String getDataRecebimentoVeiculoDocumento() {
		return dataRecebimentoVeiculoDocumento;
	}

	public Long getId() {
		return id;
	}

	public String getLocalVeiculoDocumento() {
		return localVeiculoDocumento;
	}

	public String getNomeRecebedorVeiculoDocumento() {
		return nomeRecebedorVeiculoDocumento;
	}

	public String getRgNumeroRecebedorVeiculoDocumento() {
		return rgNumeroRecebedorVeiculoDocumento;
	}

	public String getRgOrgaoEmisssorRecebedorVeiculoDocumento() {
		return rgOrgaoEmisssorRecebedorVeiculoDocumento;
	}

	public String getSituacaoVeiculoDocumento() {
		return situacaoVeiculoDocumento;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setCnpjRecebedorVeiculoDocumento(String cnpjRecebedorVeiculoDocumento) {
		this.cnpjRecebedorVeiculoDocumento = cnpjRecebedorVeiculoDocumento;
	}

	public void setCpfRecebedorVeiculoDocumento(String cpfRecebedorVeiculoDocumento) {
		this.cpfRecebedorVeiculoDocumento = cpfRecebedorVeiculoDocumento;
	}

	public void setDataDevolucaoVeiculoDocumento(String dataDevolucaoVeiculoDocumento) {
		this.dataDevolucaoVeiculoDocumento = dataDevolucaoVeiculoDocumento;
	}

	public void setDataRecebimentoVeiculoDocumento(String dataRecebimentoVeiculoDocumento) {
		this.dataRecebimentoVeiculoDocumento = dataRecebimentoVeiculoDocumento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLocalVeiculoDocumento(String localVeiculoDocumento) {
		this.localVeiculoDocumento = localVeiculoDocumento;
	}

	public void setNomeRecebedorVeiculoDocumento(String nomeRecebedorVeiculoDocumento) {
		this.nomeRecebedorVeiculoDocumento = nomeRecebedorVeiculoDocumento;
	}

	public void setRgNumeroRecebedorVeiculoDocumento(String rgNumeroRecebedorVeiculoDocumento) {
		this.rgNumeroRecebedorVeiculoDocumento = rgNumeroRecebedorVeiculoDocumento;
	}

	public void setRgOrgaoEmisssorRecebedorVeiculoDocumento(String rgOrgaoEmisssorRecebedorVeiculoDocumento) {
		this.rgOrgaoEmisssorRecebedorVeiculoDocumento = rgOrgaoEmisssorRecebedorVeiculoDocumento;
	}

	public void setSituacaoVeiculoDocumento(String situacaoVeiculoDocumento) {
		this.situacaoVeiculoDocumento = situacaoVeiculoDocumento;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public String toString() {
		return this.veiculo.getPlaca();
	}

}
