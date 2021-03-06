package erp.negocio.centrocusto;

import java.util.Collection;

public final class CentroCustoFac {

	private static final CentroCustoDao centroCustoDao = new CentroCustoImp();

	public static void deletarRegistro(CentroCusto centroCusto) {

		centroCustoDao.deletarRegistro(centroCusto);
	}

	public static Collection<CentroCusto> getRegistro() {

		return centroCustoDao.getRegistro();
	}

	public static CentroCusto getRegistro(CentroCusto centroCusto) {

		return centroCustoDao.getRegistro(centroCusto);
	}

	public static Collection<CentroCusto> pesquisarRegistro(CentroCusto centroCusto) {

		return centroCustoDao.pesquisarRegistro(centroCusto);
	}

	public static void salvarRegistro(CentroCusto centroCusto) {

		centroCustoDao.salvarRegistro(centroCusto);
	}

	private CentroCustoFac() {
	}
}
