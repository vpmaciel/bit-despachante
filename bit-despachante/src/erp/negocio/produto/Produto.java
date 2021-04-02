package erp.negocio.produto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import erp.negocio.fornecedor.Fornecedor;
import erp.negocio.produto.categoria.ProdutoCategoria;
import erp.negocio.produto.marca.ProdutoMarca;
import erp.negocio.produto.unidade.ProdutoUnidade;

@SuppressWarnings("serial")
@PersistenceContext(unitName = "erp")
@Entity
@Table(indexes = { @Index(name = "INDEX_Produto_NOME", columnList = "nome", unique = true),
		@Index(name = "INDEX_Produto_CODIGO", columnList = "preco", unique = true) })
public class Produto implements Serializable {

	private double estoqueAtual;
	private double estoqueMinimo;
	private Fornecedor fornecedor;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 50, nullable = false)
	private String nome;
	private double preco;
	private ProdutoCategoria produtoCategoria;
	private ProdutoMarca produtoMarca;
	private ProdutoUnidade produtoUnidade;

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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public double getEstoqueAtual() {
		return estoqueAtual;
	}

	public double getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public double getPreco() {
		return this.preco;
	}

	public ProdutoCategoria getProdutoCategoria() {
		return produtoCategoria;
	}

	public ProdutoMarca getProdutoMarca() {
		return produtoMarca;
	}

	public ProdutoUnidade getProdutoUnidade() {
		return produtoUnidade;
	}

	public void setEstoqueAtual(double estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public void setEstoqueMinimo(double estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setProdutoCategoria(ProdutoCategoria produtoCategoria) {
		this.produtoCategoria = produtoCategoria;
	}

	public void setProdutoMarca(ProdutoMarca produtoMarca) {
		this.produtoMarca = produtoMarca;
	}

	public void setProdutoUnidade(ProdutoUnidade produtoUnidade) {
		this.produtoUnidade = produtoUnidade;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
