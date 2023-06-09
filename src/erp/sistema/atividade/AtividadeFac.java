package erp.sistema.atividade;

import java.util.Collection;

public final class AtividadeFac {

	private static final AtividadeDao atividadeDao = new AtividadeImp();

	public static void deletarRegistro(Atividade atividade) {
		atividadeDao.deletarRegistro(atividade);
	}

	public static Collection<Atividade> getRegistro() {
		return atividadeDao.getRegistro();
	}

	public static Atividade getRegistro(Atividade atividade) {
		return atividadeDao.getRegistro(atividade);
	}

	public static boolean isRegistroValido(Atividade atividade) {
		return atividadeDao.isRegistroValido(atividade);
	}

	public static Collection<Atividade> pesquisarRegistro(Atividade atividade) {
		return atividadeDao.pesquisarRegistro(atividade);
	}

	public static void salvarRegistro(Atividade atividade) {
		atividadeDao.salvarRegistro(atividade);
	}

	private AtividadeFac() {
	}
}
