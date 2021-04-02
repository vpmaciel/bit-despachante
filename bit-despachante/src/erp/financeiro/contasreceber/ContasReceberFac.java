package erp.financeiro.contasreceber;

import java.util.Collection;

public final class ContasReceberFac {

	private static final ContasReceberDao contasReceberDao = new ContasReceberImp();

	public static void deletarRegistro(ContasReceber contasReceber) {

		contasReceberDao.deletarRegistro(contasReceber);
	}

	public static Collection<ContasReceber> getRegistro() {

		return contasReceberDao.getRegistro();
	}

	public static ContasReceber getRegistro(ContasReceber contasReceber) {

		return contasReceberDao.getRegistro(contasReceber);
	}

	public static Collection<ContasReceber> pesquisarRegistro(ContasReceber contasReceber) {

		return contasReceberDao.pesquisarRegistro(contasReceber);
	}

	public static void salvarRegistro(ContasReceber contasReceber) {

		contasReceberDao.salvarRegistro(contasReceber);
	}

	private ContasReceberFac() {
	}
}
