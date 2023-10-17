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
	@Column(length = 14, name = "CLIENTE_CPF_CNPJ")
	private String cpfCnpj;
	@Column(length = 70, name = "CLIENTE_EMAIL")
	private String email;
	@Column(length = 50, name = "CLIENTE_TELEFONE")
	private String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return this.cpfCnpj;
	}
}
