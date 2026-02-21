package erp.usuario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(name = "USUARIO", uniqueConstraints = { @UniqueConstraint(columnNames = { "NOME" }) })

public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USUARIO_IDENTIFICADOR")
    private Long id;
    @Column(length = 100, name = "NOME")
    private String nome;
    @Column(length = 200, name = "SENHA")
    private String senha;
    @Column(length = 200, name = "EMAIL")
    private String email;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro = new Date();

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

    public Date getDataCadastro() {
	return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
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
