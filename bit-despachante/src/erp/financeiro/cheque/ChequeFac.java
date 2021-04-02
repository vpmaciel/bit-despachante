package erp.financeiro.cheque;

import java.util.Collection;

public final class ChequeFac {

	private static final ChequeDao chequeDao = new ChequeImp();

	public static void deletarRegistro(Cheque cheque) {

		chequeDao.deletarRegistro(cheque);
	}

	public static Collection<Cheque> getRegistro() {

		return chequeDao.getRegistro();
	}

	public static Cheque getRegistro(Cheque cheque) {

		return chequeDao.getRegistro(cheque);
	}

	public static Collection<Cheque> pesquisarRegistro(Cheque cheque) {

		return chequeDao.pesquisarRegistro(cheque);
	}

	public static void salvarRegistro(Cheque cheque) {

		chequeDao.salvarRegistro(cheque);
	}

	private ChequeFac() {
	}
}
