package erp.servico;

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
import erp.arquitetura.Numero;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(name = "SERVICO")

public class Servico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SERVICO_IDENTIFICADOR")
    private Long id = null;
    @Column(name = "DATA", columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private Date data = new Date();
    @Column(length = 8, name = "PLACA_VEICULO")
    private String placa;
    @Column(name = "VALOR")
    private Float valor;
    @Column(length = 50, name = "DESCRICAO")
    private String descricao;
    @Column(length = 20, name = "CPF_CNPJ_CLIENTE")
    private String cpfCnpjCliente;
    @Column(length = 50, name = "NOME_CLIENTE")
    private String nomeCliente;
    @Column(length = 15, name = "TELEFONE_CLIENTE")
    private String telefoneCliente;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro = new Date();

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if ((obj == null) || (getClass() != obj.getClass()))
	    return false;
	Servico other = (Servico) obj;
	return Objects.equals(id, other.id);
    }

    public Date getDataCadastro() {
	return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
    }

    public String getCpfCnpjCliente() {
	return cpfCnpjCliente;
    }

    public Date getData() {
	return data;
    }

    public String getDataFormatada() {
	if (data == null) {
	    return null;
	}

	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");

	return sdf.format(data);
    }

    public String getDescricao() {
	return descricao;
    }

    public Long getId() {
	return id;
    }

    public String getNomeCliente() {
	return nomeCliente;
    }

    public String getPlaca() {
	return placa;
    }

    public String getTelefoneCliente() {
	return telefoneCliente;
    }

    public Float getValor() {
	return valor;
    }

    public String getValorFormatado() {
	return Numero.FloatToString(valor);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    public void setCpfCnpjCliente(String cpfCnpjCliente) {
	this.cpfCnpjCliente = cpfCnpjCliente;
    }

    public void setData(Date data) {
	this.data = data;
    }

    public void setDescricao(String descricao) {
	this.descricao = Formatacao.removerAcentos(descricao);
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setNomeCliente(String nomeCliente) {
	this.nomeCliente = Formatacao.removerAcentos(nomeCliente);
    }

    public void setPlaca(String placa) {
	this.placa = placa;
    }

    public void setTelefoneCliente(String telefoneCliente) {
	this.telefoneCliente = telefoneCliente;
    }

    public void setValor(Float valor) {
	this.valor = valor;
    }

    @Override
    public String toString() {
	return this.descricao;
    }
}
