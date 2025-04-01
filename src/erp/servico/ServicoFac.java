package erp.servico;

import java.util.Collection;

public final class ServicoFac {

    private static final ServicoDao marcaDao = new ServicoImp();

    public static void deletarRegistro(Servico marca) {
	marcaDao.deletarRegistro(marca);
    }

    public static Collection<Servico> getRegistro() {
	return marcaDao.getRegistro();
    }

    public static Servico getRegistro(Servico marca) {
	return marcaDao.getRegistro(marca);
    }

    public static boolean isRegistroValido(Servico marca) {
	return marcaDao.isRegistroValido(marca);
    }

    public static Collection<Servico> pesquisarRegistro(Servico marca) {
	return marcaDao.pesquisarRegistro(marca);
    }

    public static void salvarRegistro(Servico marca) {
	marcaDao.salvarRegistro(marca);
    }

    private ServicoFac() {
    }
}
