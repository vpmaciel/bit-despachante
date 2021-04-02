package erp.escritorio.veiculo.pedido.placa;

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
@Table(indexes = { @Index(name = "INDEX_CENTRO_CUSTO_NOME", columnList = "nome", unique = true) })
public class PedidoPlaca implements Serializable {

	@Column(length = 50)
	private String cidade;
	@Column(length = 50)
	private String cor;
	@Column(length = 50)
	private String estado;
	@Column(length = 50)
	private String fazerPlaca;
	@Column(length = 50)
	private String fazerTarjeta;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 50, nullable = false)
	private String nome;
	@Column(length = 50)
	private String placa;
	@Column(length = 50)
	private String renavam;

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
		PedidoPlaca other = (PedidoPlaca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCidade() {
		return cidade;
	}

	public String getCor() {
		return cor;
	}

	public String getEstado() {
		return estado;
	}

	public String getFazerPlaca() {
		return fazerPlaca;
	}

	public String getFazerTarjeta() {
		return fazerTarjeta;
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getPlaca() {
		return placa;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setFazerPlaca(String fazerPlaca) {
		this.fazerPlaca = fazerPlaca;
	}

	public void setFazerTarjeta(String fazerTarjeta) {
		this.fazerTarjeta = fazerTarjeta;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
