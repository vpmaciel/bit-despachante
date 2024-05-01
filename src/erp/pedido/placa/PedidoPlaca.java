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

import erp.arquitetura.Formatacao;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(name = "PEDIDO_DE_PLACA")

public class PedidoPlaca implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PEDIDO_DE_PLACA_ID")
	private Long id = null;
	@Column(name = "PEDIDO_DE_PLACA_DATA", columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@Column(length = 8, name = "PEDIDO_DE_PLACA_PLACA_VEICULO")
	private String placa;
	@Column(name = "PEDIDO_DE_PLACA_QUANTIDADE")
	private Integer quantidade;
	@Column(length = 50, name = "PEDIDO_DE_PLACA_RENAVAM")
	private String renavam;
	@Column(length = 20, name = "PEDIDO_DE_PLACA_CPF_CNPJ_PROPRIETARIO")
	private String cpfCnpjProprietario;
	@Column(length = 50, name = "PEDIDO_DE_PLACA_COR_PLACA")
	private String corPlaca;
	@Column(length = 50, name = "PEDIDO_DE_PLACA_TIPO_PLACA")
	private String tipoPlaca;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		PedidoPlaca other = (PedidoPlaca) obj;
		return Objects.equals(id, other.id);
	}

	public String getCorPlaca() {
		return corPlaca;
	}

	public String getCpfCnpjProprietario() {
		return cpfCnpjProprietario;
	}

	public Date getData() {
		return data;
	}

	public Long getId() {
		return id;
	}

	public String getPlaca() {
		return placa;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getRenavam() {
		return renavam;
	}

	public String getTipoPlaca() {
		return tipoPlaca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void setCorPlaca(String corPlaca) {
		this.corPlaca = Formatacao.removerAcentos(corPlaca);
	}

	public void setCpfCnpjProprietario(String cpfCnpjCliente) {
		this.cpfCnpjProprietario = cpfCnpjCliente;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public void setTipoPlaca(String tipoPlaca) {
		this.tipoPlaca = Formatacao.removerAcentos(tipoPlaca);
	}

	@Override
	public String toString() {
		return this.placa;
	}
}
