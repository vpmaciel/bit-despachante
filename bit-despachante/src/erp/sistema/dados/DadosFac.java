package erp.sistema.dados;

import java.util.Collection;

public final class DadosFac {

	private static final DadosDao contadorDao = new DadosImp();

	public static void deletarRegistro(Dados contador) {
		contadorDao.deletarRegistro(contador);
	}

	public static Collection<Dados> getRegistro() {
		return contadorDao.getRegistro();
	}

	public static Dados getRegistro(Dados contador) {
		return contadorDao.getRegistro(contador);
	}

	public static Collection<Dados> pesquisarRegistro(Dados contador) {
		return contadorDao.pesquisarRegistro(contador);
	}

	public static void salvarRegistro(Dados contador) {
		contadorDao.salvarRegistro(contador);
	}

	private DadosFac() {
	}
}
