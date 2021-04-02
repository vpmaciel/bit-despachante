package erp.financeiro.caixa;

import java.util.Collection;

public final class CaixaFac {

	private static final CaixaDao caixaDao = new CaixaImp();

	public static void deletarRegistro(Caixa caixa) {

		caixaDao.deletarRegistro(caixa);
	}

	public static Collection<Caixa> getRegistro() {

		return caixaDao.getRegistro();
	}

	public static Caixa getRegistro(Caixa caixa) {

		return caixaDao.getRegistro(caixa);
	}

	public static Collection<Caixa> pesquisarRegistro(Caixa caixa) {

		return caixaDao.pesquisarRegistro(caixa);
	}

	public static void salvarRegistro(Caixa caixa) {

		caixaDao.salvarRegistro(caixa);
	}

	private CaixaFac() {
	}
}
