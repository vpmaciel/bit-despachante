package erp.cliente;

import java.util.Collection;
import java.util.Map;

public interface ClienteDao {

    void deletarRegistro(Cliente marca);

    Collection<Cliente> getRegistro();

    Cliente getRegistro(Cliente marca);

    Collection<Cliente> pesquisarRegistro(Cliente cliente);
    
    public Map<Integer, Long> pesquisarTotalClientesPorMes();

    void salvarRegistro(Cliente marca);
}
