package erp.arquitetura;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(name = "CHAVE")
public class Chave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 100, name = "CHAVE_IDENTIFICADOR")
    private Long id = null;
    @Column(length = 50, name = "CHAVE_DESCRICAO")
    private String descricao;

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if ((obj == null) || (getClass() != obj.getClass()))
	    return false;
	Chave other = (Chave) obj;
	return Objects.equals(id, other.id);
    }

    public Long getId() {
	return id;
    }

    public String getDescricao() {
	return descricao;
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    @Override
    public String toString() {
	return this.descricao;
    }
}
