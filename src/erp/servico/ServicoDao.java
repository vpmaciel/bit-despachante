package erp.servico;

import java.util.Collection;

public interface ServicoDao {

    void deletarRegistro(Servico marca);

    Collection<Servico> getRegistro();

    Servico getRegistro(Servico marca);

    boolean isRegistroValido(Servico marca);

    Collection<Servico> pesquisarRegistro(Servico marca);

    void salvarRegistro(Servico marca);
}
