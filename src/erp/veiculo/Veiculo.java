package erp.veiculo;

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
import javax.persistence.UniqueConstraint;

import erp.arquitetura.Formatacao;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(name = "VEICULO", uniqueConstraints = { @UniqueConstraint(columnNames = { "PLACA_VEICULO" }) })
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VEICULO_IDENTIFICADOR")
    private Long id = null;
    @Column(length = 8, name = "PLACA_VEICULO")
    private String placa;
    @Column(length = 50, name = "MARCA_VEICULO")
    private String marca;
    @Column(length = 20, name = "CPF_CNPJ_PROPRIETARIO")
    private String cpfCnpjProprietario;
    @Column(length = 50, name = "NOME_PROPRIETARIO")
    private String nomeProprietario;
    @Column(length = 50, name = "MODELO_VEICULO")
    private String modelo;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro = new Date();

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if ((obj == null) || (getClass() != obj.getClass()))
	    return false;
	Veiculo other = (Veiculo) obj;
	return Objects.equals(id, other.id);
    }

    public Date getDataCadastro() {
	return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
    }

    public String getCpfCnpjProprietario() {
	return cpfCnpjProprietario;
    }

    public Long getId() {
	return id;
    }

    public String getMarca() {
	return marca;
    }

    public String getModelo() {
	return modelo;
    }

    public String getNomeProprietario() {
	return nomeProprietario;
    }

    public String getPlaca() {
	return placa;
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    public void setCpfCnpjProprietario(String cpfCnpjProprietario) {
	this.cpfCnpjProprietario = Formatacao.removerAcentos(cpfCnpjProprietario);
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setMarca(String marca) {
	this.marca = Formatacao.removerAcentos(marca);
    }

    public void setModelo(String modelo) {
	this.modelo = Formatacao.removerAcentos(modelo);
    }

    public void setNomeProprietario(String nomeProprietario) {
	this.nomeProprietario = Formatacao.removerAcentos(nomeProprietario);
    }

    public void setPlaca(String placa) {
	this.placa = Formatacao.removerAcentos(placa);
    }

    @Override
    public String toString() {
	return this.placa;
    }
}
