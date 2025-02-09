package erp.cliente;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import erp.arquitetura.Formatacao;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(name = "CLIENTE", uniqueConstraints = { @UniqueConstraint(columnNames = { "CLIENTE_CPF_CNPJ" }) }, indexes = {
		@Index(name = "INDEX_CLIENTE_CPF_CNPJ", columnList = "CLIENTE_CPF_CNPJ", unique = true),
		@Index(name = "INDEX_NOME", columnList = "CLIENTE_NOME", unique = false) })

public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 100, name = "CLIENTE_IDENTIFICADOR")
	private Long id = null;
	@Column(length = 50, name = "CLIENTE_NOME")
	private String nome;
	@Column(length = 20, name = "CLIENTE_CPF_CNPJ")
	private String cpfCnpj;
	@Column(length = 70, name = "CLIENTE_EMAIL")
	private String email;
	@Column(length = 15, name = "CLIENTE_TELEFONE")
	private String telefone;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setEmail(String email) {
		this.email = Formatacao.removerAcentos(email);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = Formatacao.removerAcentos(nome);
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return this.cpfCnpj;
	}
}
