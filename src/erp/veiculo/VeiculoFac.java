package erp.veiculo;

import java.util.Collection;

public final class VeiculoFac {

	private static final VeiculoDao marcaDao = new VeiculoImp();

	public static void deletarRegistro(Veiculo marca) {
		marcaDao.deletarRegistro(marca);
	}

	public static Collection<Veiculo> getRegistro() {
		return marcaDao.getRegistro();
	}

	public static Veiculo getRegistro(Veiculo marca) {
		return marcaDao.getRegistro(marca);
	}

	public static Collection<Veiculo> pesquisarRegistro(Veiculo marca) {
		return marcaDao.pesquisarRegistro(marca);
	}

	public static void salvarRegistro(Veiculo marca) {
		marcaDao.salvarRegistro(marca);
	}

	private VeiculoFac() {
	}
}
