package erp.escritorio.documento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
public class Documento implements Serializable {

	@Column(length = 4)
	private String anoDevolucaoDocumento;
	@Column(length = 4)
	private String anoRecebimentoDocumento;
	@Column(length = 19)
	private String cnpjRecebedorDocumento;
	@Column(length = 14)
	private String cpfRecebedorDocumento;
	@Column(length = 50)
	private String descricao;
	@Column(length = 3)
	private String diaDevolucaoDocumento;
	@Column(length = 2)
	private String diaRecebimentoDocumento;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 15)
	private String localDocumento;
	@Column(length = 15)
	private String mesDevolucaoDocumento;
	@Column(length = 15)
	private String mesRecebimentoDocumento;
	@Column(length = 50)
	private String nomeProprietario;
	@Column(length = 50)
	private String nomeRecebedorDocumento;
	@Column(length = 15)
	private String rgNumeroRecebedorDocumento;
	@Column(length = 15)
	private String rgOrgaoEmisssorRecebedorDocumento;
	@Column(length = 15)
	private String situacaoDocumento;
	@Column(length = 50)
	private String tipoDocumento;

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
		Documento other = (Documento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getAnoDevolucaoDocumento() {
		return anoDevolucaoDocumento;
	}

	public String getAnoRecebimentoDocumento() {
		return anoRecebimentoDocumento;
	}

	public String getCnpjRecebedorDocumento() {
		return cnpjRecebedorDocumento;
	}

	public String getCpfRecebedorDocumento() {
		return cpfRecebedorDocumento;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDiaDevolucaoDocumento() {
		return diaDevolucaoDocumento;
	}

	public String getDiaRecebimentoDocumento() {
		return diaRecebimentoDocumento;
	}

	public Long getId() {
		return id;
	}

	public String getLocalDocumento() {
		return localDocumento;
	}

	public String getMesDevolucaoDocumento() {
		return mesDevolucaoDocumento;
	}

	public String getMesRecebimentoDocumento() {
		return mesRecebimentoDocumento;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public String getNomeRecebedorDocumento() {
		return nomeRecebedorDocumento;
	}

	public String getRgNumeroRecebedorDocumento() {
		return rgNumeroRecebedorDocumento;
	}

	public String getRgOrgaoEmisssorRecebedorDocumento() {
		return rgOrgaoEmisssorRecebedorDocumento;
	}

	public String getSituacaoDocumento() {
		return situacaoDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setAnoDevolucaoDocumento(String anoDevolucaoDocumento) {
		this.anoDevolucaoDocumento = anoDevolucaoDocumento;
	}

	public void setAnoRecebimentoDocumento(String anoRecebimentoDocumento) {
		this.anoRecebimentoDocumento = anoRecebimentoDocumento;
	}

	public void setCnpjRecebedorDocumento(String cnpjRecebedorDocumento) {
		this.cnpjRecebedorDocumento = cnpjRecebedorDocumento;
	}

	public void setCpfRecebedorDocumento(String cpfRecebedorDocumento) {
		this.cpfRecebedorDocumento = cpfRecebedorDocumento;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDiaDevolucaoDocumento(String diaDevolucaoDocumento) {
		this.diaDevolucaoDocumento = diaDevolucaoDocumento;
	}

	public void setDiaRecebimentoDocumento(String diaRecebimentoDocumento) {
		this.diaRecebimentoDocumento = diaRecebimentoDocumento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLocalDocumento(String localDocumento) {
		this.localDocumento = localDocumento;
	}

	public void setMesDevolucaoDocumento(String mesDevolucaoDocumento) {
		this.mesDevolucaoDocumento = mesDevolucaoDocumento;
	}

	public void setMesRecebimentoDocumento(String mesRecebimentoDocumento) {
		this.mesRecebimentoDocumento = mesRecebimentoDocumento;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public void setNomeRecebedorDocumento(String nomeRecebedorDocumento) {
		this.nomeRecebedorDocumento = nomeRecebedorDocumento;
	}

	public void setRgNumeroRecebedorDocumento(String rgNumeroRecebedorDocumento) {
		this.rgNumeroRecebedorDocumento = rgNumeroRecebedorDocumento;
	}

	public void setRgOrgaoEmisssorRecebedorDocumento(String rgOrgaoEmisssorRecebedorDocumento) {
		this.rgOrgaoEmisssorRecebedorDocumento = rgOrgaoEmisssorRecebedorDocumento;
	}

	public void setSituacaoDocumento(String situacaoDocumento) {
		this.situacaoDocumento = situacaoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

}
