package erp.negocio.veiculo.modelo;

import java.util.Collection;

public final class VeiculoModeloFac {

	private static final VeiculoModeloDao veiculoModeloDao = new VeiculoModeloImp();

	public static void deletarRegistro(VeiculoModelo veiculoModelo) {

		veiculoModeloDao.deletarRegistro(veiculoModelo);
	}

	public static Collection<VeiculoModelo> getRegistro() {

		return veiculoModeloDao.getRegistro();
	}

	public static VeiculoModelo getRegistro(VeiculoModelo veiculoModelo) {

		return veiculoModeloDao.getRegistro(veiculoModelo);
	}

	public static Collection<VeiculoModelo> pesquisarRegistro(VeiculoModelo veiculoModelo) {

		return veiculoModeloDao.pesquisarRegistro(veiculoModelo);
	}

	public static void salvarRegistro(VeiculoModelo veiculoModelo) {

		veiculoModeloDao.salvarRegistro(veiculoModelo);
	}

	private VeiculoModeloFac() {
	}
}
