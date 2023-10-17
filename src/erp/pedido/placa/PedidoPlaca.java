package erp.pedido.placa;

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
@Table(name = "PEDIDO_DE_PLACA")

public class PedidoPlaca implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 100, name = "PEDIDO_DE_PLACA_ID")
	private Long id = null;
	@Column(name = "PEDIDO_DE_PLACA_DATA", columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@Column(length = 7, name = "PEDIDO_DE_PLACA_PLACA_VEICULO")
	private String placa;
	@Column(name = "PEDIDO_DE_PLACA_QUANTIDADE")
	private Integer quantidade;
	@Column(length = 50, name = "PEDIDO_DE_PLACA_RENAVAM")
	private String renavam;
	@Column(length = 18, name = "PEDIDO_DE_PLACA_CPF_CNPJ_PROPRIETARIO")
	private String cpfCnpjProprietario;
	@Column(length = 50, name = "PEDIDO_DE_PLACA_COR_PLACA")
	private String corPlaca;
	@Column(length = 50, name = "PEDIDO_DE_PLACA_TIPO_PLACA")
	private String tipoPlaca;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getCpfCnpjProprietario() {
		return cpfCnpjProprietario;
	}

	public void setCpfCnpjProprietario(String cpfCnpjCliente) {
		this.cpfCnpjProprietario = cpfCnpjCliente;
	}
	
	public String getCorPlaca() {
		return corPlaca;
	}

	public void setCorPlaca(String corPlaca) {
		this.corPlaca = corPlaca;
	}

	
	public String getTipoPlaca() {
		return tipoPlaca;
	}

	public void setTipoPlaca(String tipoPlaca) {
		this.tipoPlaca = tipoPlaca;
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
		PedidoPlaca other = (PedidoPlaca) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return this.renavam;
	}
}
