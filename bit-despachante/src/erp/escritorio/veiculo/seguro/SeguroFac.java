package erp.escritorio.veiculo.seguro;

import java.util.Collection;

public final class SeguroFac {

	private static final SeguroDao seguroDao = new SeguroImp();

	public static void deletarRegistro(Seguro seguro) {

		seguroDao.deletarRegistro(seguro);
	}

	public static Collection<Seguro> getRegistro() {

		return seguroDao.getRegistro();
	}

	public static Seguro getRegistro(Seguro seguro) {

		return seguroDao.getRegistro(seguro);
	}

	public static Collection<Seguro> pesquisarRegistro(Seguro seguro) {

		return seguroDao.pesquisarRegistro(seguro);
	}

	public static void salvarRegistro(Seguro seguro) {

		seguroDao.salvarRegistro(seguro);
	}

	private SeguroFac() {
	}
}
