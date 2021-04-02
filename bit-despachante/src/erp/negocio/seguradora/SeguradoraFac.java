package erp.negocio.seguradora;

import java.util.Collection;

public final class SeguradoraFac {

	private static final SeguradoraDao seguradoraDao = new SeguradoraImp();

	public static void deletarRegistro(Seguradora seguradora) {

		seguradoraDao.deletarRegistro(seguradora);
	}

	public static Collection<Seguradora> getRegistro() {

		return seguradoraDao.getRegistro();
	}

	public static Seguradora getRegistro(Seguradora seguradora) {

		return seguradoraDao.getRegistro(seguradora);
	}

	public static Collection<Seguradora> pesquisarRegistro(Seguradora seguradora) {

		return seguradoraDao.pesquisarRegistro(seguradora);
	}

	public static void salvarRegistro(Seguradora seguradora) {

		seguradoraDao.salvarRegistro(seguradora);
	}

	private SeguradoraFac() {
	}
}
