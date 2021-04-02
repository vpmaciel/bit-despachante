package erp.utilitarios.agenda.recado;

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
public class Recado implements Serializable {
	@Column(length = 10)
	private String data;
	@Column(length = 50)
	private String destinatario;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 500)
	private String recado;
	@Column(length = 50)
	private String remetente;



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
		Recado other = (Recado) obj;
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

	public String getDestinatario() {
		return destinatario;
	}

	public Long getId() {
		return id;
	}

	public String getRecado() {
		return recado;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRecado(String recado) {
		this.recado = recado;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	@Override
	public String toString() {
		return this.data;
	}
}