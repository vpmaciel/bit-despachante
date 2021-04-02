package erp.negocio.cliente;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import erp.negocio.empresa.Empresa;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(indexes = { @Index(name = "INDEX_CLIENTE_CPF", columnList = "cpf", unique = true),
		@Index(name = "INDEX_CLIENTE_CNPJ", columnList = "cnpj", unique = true) })

public class Cliente implements Serializable {

	@Column(length = 50)
	private String cargo;
	@Column(length = 19)
	private String cnpj;
	@Column(length = 14)
	private String cpf;
	@Column(length = 10)
	private String dataNascimento;
	@Column(length = 22)
	private String deficiencia;
	@Column(length = 50)
	private String email;
	@ManyToOne(cascade = CascadeType.ALL)
	private Empresa empresa;
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
	@Column(length = 14)
	private String enderecoEstadoCivil;
	@Column(length = 50)
	private String enderecoLogradouro;
	@Column(length = 50)
	private String enderecoPais;
	@Column(length = 24)
	private String escolaridade;
	@Column(length = 20)
	private String fone1;
	@Column(length = 20)
	private String fone2;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	@Column(length = 15)
	private String rgNumero;
	@Column(length = 20)
	private String rgOrgaoEmissor;
	@Column(length = 10)
	private String salario;
	@Column(length = 9)
	private String sexo;
	@Column(length = 7)
	private String status;

	public Cliente() {

	}

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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public String getCargo() {
		return this.cargo;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public String getCpf() {
		return this.cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getDeficiencia() {
		return deficiencia;
	}

	public String getEmail() {
		return this.email;
	}

	public Empresa getEmpresa() {
		return this.empresa;
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

	public String getEscolaridade() {
		return escolaridade;
	}

	public String getEstadoCivil() {
		return this.enderecoEstadoCivil;
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

	public String getNome() {
		return this.nome;
	}

	public String getRgNumero() {
		return this.rgNumero;
	}

	public String getRgOrgaoEmissor() {
		return this.rgOrgaoEmissor;
	}

	public String getSalario() {
		return this.salario;
	}

	public String getSexo() {
		return this.sexo;
	}

	public String getStatus() {
		return status;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setDeficiencia(String deficiencia) {
		this.deficiencia = deficiencia;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public void setEstadoCivil(String enderecoEstadoCivil) {
		this.enderecoEstadoCivil = enderecoEstadoCivil;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRgNumero(String rgNumero) {
		this.rgNumero = rgNumero;
	}

	public void setRgOrgaoEmissor(String rgOrgaoEmissor) {
		this.rgOrgaoEmissor = rgOrgaoEmissor;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.nome + " - " + this.cpf;
	}
}
