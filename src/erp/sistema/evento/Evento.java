package erp.sistema.evento;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
public class Evento implements Serializable {
	@Column(length = 10)
	private String data;
	@Column(length = 50)
	private String descricao;
	@Column(length = 5)
	private String horaInicio;
	@Column(length = 5)
	private String horaTermino;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 5)
	private String tipoEvento;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Evento other = (Evento) obj;
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

	public String getDescricao() {
		return descricao;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraTermino() {
		return horaTermino;
	}

	public Long getId() {
		return id;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	@Override
	public String toString() {
		return this.descricao;
	}
}
