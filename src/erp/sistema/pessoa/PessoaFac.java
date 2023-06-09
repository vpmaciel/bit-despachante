package erp.sistema.pessoa;

import java.util.Collection;

public final class PessoaFac {

	private static final PessoaDao pessoaDao = new PessoaImp();

	public static void deletarRegistro(Pessoa pessoa) {
		pessoaDao.deletarRegistro(pessoa);
	}

	public static Collection<Pessoa> getRegistro() {
		return pessoaDao.getRegistro();
	}

	public static Pessoa getRegistro(Pessoa pessoa) {
		return pessoaDao.getRegistro(pessoa);
	}

	public static boolean isRegistroValido(Pessoa pessoa) {
		return pessoaDao.isRegistroValido(pessoa);
	}

	public static Collection<Pessoa> pesquisarRegistro(Pessoa pessoa) {
		return pessoaDao.pesquisarRegistro(pessoa);
	}

	public static void salvarRegistro(Pessoa pessoa) {
		pessoaDao.salvarRegistro(pessoa);
	}

	private PessoaFac() {
	}
}
