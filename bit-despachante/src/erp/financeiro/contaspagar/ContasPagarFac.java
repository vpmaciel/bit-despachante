package erp.financeiro.contaspagar;

import java.util.Collection;

public final class ContasPagarFac {

	private static final ContasPagarDao contasPagarDao = new ContasPagarImp();

	public static void deletarRegistro(ContasPagar contasPagar) {

		contasPagarDao.deletarRegistro(contasPagar);
	}

	public static Collection<ContasPagar> getRegistro() {

		return contasPagarDao.getRegistro();
	}

	public static ContasPagar getRegistro(ContasPagar contasPagar) {

		return contasPagarDao.getRegistro(contasPagar);
	}

	public static Collection<ContasPagar> pesquisarRegistro(ContasPagar contasPagar) {

		return contasPagarDao.pesquisarRegistro(contasPagar);
	}

	public static void salvarRegistro(ContasPagar contasPagar) {

		contasPagarDao.salvarRegistro(contasPagar);
	}

	private ContasPagarFac() {
	}
}
