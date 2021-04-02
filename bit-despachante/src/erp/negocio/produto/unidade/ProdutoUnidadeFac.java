package erp.negocio.produto.unidade;

import java.util.Collection;

public final class ProdutoUnidadeFac {

	private static final ProdutoUnidadeDao produtoUnidadeDao = new ProdutoUnidadeImp();

	public static void deletarRegistro(ProdutoUnidade produtoUnidade) {

		produtoUnidadeDao.deletarRegistro(produtoUnidade);
	}

	public static Collection<ProdutoUnidade> getRegistro() {

		return produtoUnidadeDao.getRegistro();
	}

	public static ProdutoUnidade getRegistro(ProdutoUnidade produtoUnidade) {

		return produtoUnidadeDao.getRegistro(produtoUnidade);
	}

	public static Collection<ProdutoUnidade> pesquisarRegistro(ProdutoUnidade produtoUnidade) {

		return produtoUnidadeDao.pesquisarRegistro(produtoUnidade);
	}

	public static void salvarRegistro(ProdutoUnidade produtoUnidade) {

		produtoUnidadeDao.salvarRegistro(produtoUnidade);
	}

	private ProdutoUnidadeFac() {
	}
}
