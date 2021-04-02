package erp.negocio.produto.categoria;

import java.util.Collection;

public final class ProdutoCategoriaFac {

	private static final ProdutoCategoriaDao servicoDao = new ProdutoCategoriaImp();

	public static void deletarRegistro(ProdutoCategoria servico) {

		servicoDao.deletarRegistro(servico);
	}

	public static Collection<ProdutoCategoria> getRegistro() {

		return servicoDao.getRegistro();
	}

	public static ProdutoCategoria getRegistro(ProdutoCategoria servico) {

		return servicoDao.getRegistro(servico);
	}

	public static Collection<ProdutoCategoria> pesquisarRegistro(ProdutoCategoria servico) {

		return servicoDao.pesquisarRegistro(servico);
	}

	public static void salvarRegistro(ProdutoCategoria servico) {

		servicoDao.salvarRegistro(servico);
	}

	private ProdutoCategoriaFac() {
	}
}
