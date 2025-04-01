package erp.veiculo;

import java.util.Collection;

public interface VeiculoDao {

    void deletarRegistro(Veiculo veiculo);

    Collection<Veiculo> getRegistro();

    Veiculo getRegistro(Veiculo veiculo);

    Collection<Veiculo> pesquisarRegistro(Veiculo veiculo);

    void salvarRegistro(Veiculo veiculo);
}
