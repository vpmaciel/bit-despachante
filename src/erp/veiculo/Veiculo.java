package erp.veiculo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(name = "SERVICO")

public class Veiculo implements Serializable {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 100, name = "SERVICO_ID")
	private Long id = null;
	@Column(name="SERVICO_DATA", columnDefinition = "date")
	@Temporal(TemporalType.DATE)	
	private Date data;
	@Column(length = 7, name = "SERVICO_PLACA_VEICULO")
	private String placa;
	@Column(name = "SERVICO_VALOR")
	private float valor;
	@Column(length = 50, name = "SERVICO_DESCRICAO")
	private String descricao;
	@Column(length = 18, name = "SERVICO_CPF_CNPJ_CLIENTE")
	private String cpfCnpjCliente;
	@Column(length = 50, name = "SERVICO_NOME_CLIENTE")
	private String nomeCliente;
	@Column(length = 50, name = "SERVICO_TELEFONE_CLIENTE")
	private String telefoneCliente;		
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
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
		Veiculo other = (Veiculo) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return this.descricao;
	}
}
