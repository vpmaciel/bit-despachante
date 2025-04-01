package erp.usuario;

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
@Table(indexes = { @Index(name = "INDEX_USUARIO_NOME", columnList = "nome", unique = true) })

public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100)
    private String nome;
    @Column(length = 200)
    private String senha;
    @Column(length = 100)
    private String email;

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if ((obj == null) || (getClass() != obj.getClass())) {
	    return false;
	}
	Usuario other = (Usuario) obj;
	if (id == null) {
	    if (other.id != null) {
		return false;
	    }
	} else if (!id.equals(other.id)) {
	    return false;
	}
	return true;
    }

    public Long getId() {
	return this.id;
    }

    public String getNome() {
	return this.nome;
    }

    public String getSenha() {
	return this.senha;
    }

    public String getEmail() {
	return this.email;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return this.nome;
    }
}
