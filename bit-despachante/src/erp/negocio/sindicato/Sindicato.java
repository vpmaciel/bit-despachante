package erp.negocio.sindicato;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(indexes = { @Index(name = "INDEX_SINDICATO_CNPJ", columnList = "cnpj", unique = true) })

public class Sindicato implements Serializable {
	@Column(length = 19)
	private String cnpj;
	@Column(length = 10)
	private String email;
	@Column(length = 50)
	private String enderecoBairro;
	@Column(length = 10)
	private String enderecoCep;
	@Column(length = 50)
	private String enderecoCidade;
	@Column(length = 20)
	private String enderecoComplemento;
	@Column(length = 50)
	private String enderecoEstado;
	@Column(length = 50)
	private String enderecoLogradouro;
	@Column(length = 50)
	private String enderecoPais;
	@Column(length = 20)
	private String fax;
	@Column(length = 20)
	private String fone1;
	@Column(length = 20)
	private String fone2;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nomeFantasia;
	@Column(length = 30)
	private String razaoSocial;
	@Column(length = 6)
	private String tipoSindicato;

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
		Sindicato other = (Sindicato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public String getEmail() {
		return this.email;
	}

	public String getEnderecoBairro() {
		return this.enderecoBairro;
	}

	public String getEnderecoCep() {
		return this.enderecoCep;
	}

	public String getEnderecoCidade() {
		return this.enderecoCidade;
	}

	public String getEnderecoComplemento() {
		return this.enderecoComplemento;
	}

	public String getEnderecoEstado() {
		return this.enderecoEstado;
	}

	public String getEnderecoLogradouro() {
		return this.enderecoLogradouro;
	}

	public String getEnderecoPais() {
		return this.enderecoPais;
	}

	public String getFax() {
		return this.fax;
	}

	public String getFone1() {
		return this.fone1;
	}

	public String getFone2() {
		return this.fone2;
	}

	public Long getId() {
		return this.id;
	}

	public String getNomeFantasia() {
		return this.nomeFantasia;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public String getTipoSindicato() {
		return this.tipoSindicato;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnderecoBairro(String enderecoBairro) {
		this.enderecoBairro = enderecoBairro;
	}

	public void setEnderecoCep(String enderecoCep) {
		this.enderecoCep = enderecoCep;
	}

	public void setEnderecoCidade(String enderecoCidade) {
		this.enderecoCidade = enderecoCidade;
	}

	public void setEnderecoComplemento(String enderecoComplemento) {
		this.enderecoComplemento = enderecoComplemento;
	}

	public void setEnderecoEstado(String enderecoEstado) {
		this.enderecoEstado = enderecoEstado;
	}

	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}

	public void setEnderecoPais(String enderecoPais) {
		this.enderecoPais = enderecoPais;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setRazaoSocial(String enderecoEstadoCivil) {
		this.razaoSocial = enderecoEstadoCivil;
	}

	public void setTipoSindicato(String tipoSindicato) {
		this.tipoSindicato = tipoSindicato;
	}

	@Override
	public String toString() {
		return this.nomeFantasia;
	}

}
