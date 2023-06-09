package erp.veiculo.marca;

import java.util.Collection;

public final class MarcaFac {

	private static final MarcaDao marcaDao = new MarcaImp();

	public static void deletarRegistro(Marca marca) {
		marcaDao.deletarRegistro(marca);
	}

	public static Collection<Marca> getRegistro() {
		return marcaDao.getRegistro();
	}

	public static Marca getRegistro(Marca marca) {
		return marcaDao.getRegistro(marca);
	}

	public static boolean isRegistroValido(Marca marca) {
		return marcaDao.isRegistroValido(marca);
	}

	public static Collection<Marca> pesquisarRegistro(Marca marca) {
		return marcaDao.pesquisarRegistro(marca);
	}

	public static void salvarRegistro(Marca marca) {
		marcaDao.salvarRegistro(marca);
	}

	private MarcaFac() {
	}
}
