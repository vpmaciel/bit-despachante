package erp.cliente;

import java.util.Collection;
import java.util.Map;

public final class ClienteFac {

    private static final ClienteDao marcaDao = new ClienteImp();

    public static void deletarRegistro(Cliente marca) {
	marcaDao.deletarRegistro(marca);
    }

    public static Collection<Cliente> getRegistro() {
	return marcaDao.getRegistro();
    }

    public static Cliente getRegistro(Cliente marca) {
	return marcaDao.getRegistro(marca);
    }

    public static Collection<Cliente> pesquisarRegistro(Cliente marca) {
	return marcaDao.pesquisarRegistro(marca);
    }

    public static Map<Integer, Long> pesquisarTotalClientesPorMes() {
	return marcaDao.pesquisarTotalClientesPorMes();
    }

    public static void salvarRegistro(Cliente marca) {
	marcaDao.salvarRegistro(marca);
    }

    private ClienteFac() {
    }
}
