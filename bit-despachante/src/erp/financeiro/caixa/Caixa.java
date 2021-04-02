package erp.financeiro.caixa;

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
public class Caixa implements Serializable {

	@Column(length = 10, nullable = false)
	private String data;
	private double entrada;
	private double estorno;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double saida;
	private double saldo;
	private double saldoAnterior;
	@Column(length = 50, nullable = false)
	private String usuario;

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
		Caixa other = (Caixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public Long getId() {
		return this.id;
	}

	public double getSaida() {
		return saida;
	}

	public double getSaldo() {
		return saldo;
	}

	public double getSaldoAnterior() {
		return saldoAnterior;
	}

	public String getUsuario() {
		return this.usuario;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setSaida(double saida) {
		this.saida = saida;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setSaldoAnterior(double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return this.usuario;
	}
}
