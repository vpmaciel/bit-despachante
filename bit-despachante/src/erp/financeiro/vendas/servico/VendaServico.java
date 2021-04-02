package erp.financeiro.vendas.servico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import erp.negocio.cliente.Cliente;
import erp.negocio.servico.Servico;
import erp.sistema.usuario.Usuario;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
public class VendaServico implements Serializable {

	private Cliente cliente;
	@Column(length = 10)
	private String data;
	private double entrada;
	private double estorno;
	private String hora;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double qtde;
	private Servico servico;
	private Usuario usuario;

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
		VendaServico other = (VendaServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getData() {
		return data;
	}

	public double getEntrada() {
		return entrada;
	}

	public double getEstorno() {
		return estorno;
	}

	public String getHora() {
		return hora;
	}

	public Long getId() {
		return this.id;
	}

	public double getQtde() {
		return qtde;
	}

	public Servico getServico() {
		return servico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setEntrada(double entrada) {
		this.entrada = entrada;
	}

	public void setEstorno(double estorno) {
		this.estorno = estorno;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQtde(double qtde) {
		this.qtde = qtde;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return this.servico.getNome();
	}
}
