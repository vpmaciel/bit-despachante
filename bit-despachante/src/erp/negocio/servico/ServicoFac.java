package erp.negocio.servico;

import java.util.Collection;

public final class ServicoFac {

	private static final ServicoDao servicoDao = new ServicoImp();

	public static void deletarRegistro(Servico servico) {

		servicoDao.deletarRegistro(servico);
	}

	public static Collection<Servico> getRegistro() {

		return servicoDao.getRegistro();
	}

	public static Servico getRegistro(Servico servico) {

		return servicoDao.getRegistro(servico);
	}

	public static Collection<Servico> pesquisarRegistro(Servico servico) {

		return servicoDao.pesquisarRegistro(servico);
	}

	public static void salvarRegistro(Servico servico) {

		servicoDao.salvarRegistro(servico);
	}

	private ServicoFac() {
	}
}
