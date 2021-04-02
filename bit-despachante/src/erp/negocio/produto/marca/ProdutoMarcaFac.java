package erp.negocio.produto.marca;

import java.util.Collection;

public final class ProdutoMarcaFac {

	private static final ProdutoMarcaDao produtoMarcaDao = new ProdutoMarcaImp();

	public static void deletarRegistro(ProdutoMarca produtoMarca) {

		produtoMarcaDao.deletarRegistro(produtoMarca);
	}

	public static Collection<ProdutoMarca> getRegistro() {

		return produtoMarcaDao.getRegistro();
	}

	public static ProdutoMarca getRegistro(ProdutoMarca produtoMarca) {

		return produtoMarcaDao.getRegistro(produtoMarca);
	}

	public static Collection<ProdutoMarca> pesquisarRegistro(ProdutoMarca produtoMarca) {

		return produtoMarcaDao.pesquisarRegistro(produtoMarca);
	}

	public static void salvarRegistro(ProdutoMarca produtoMarca) {

		produtoMarcaDao.salvarRegistro(produtoMarca);
	}

	private ProdutoMarcaFac() {
	}
}
