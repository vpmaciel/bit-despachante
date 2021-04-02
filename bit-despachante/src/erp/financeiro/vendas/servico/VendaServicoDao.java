package erp.financeiro.vendas.servico;

import java.util.Collection;

interface VendaServicoDao {

	void deletarRegistro(VendaServico vendaServico);

	Collection<VendaServico> getRegistro();

	VendaServico getRegistro(VendaServico vendaServico);

	Collection<VendaServico> pesquisarRegistro(VendaServico vendaServico);

	void salvarRegistro(VendaServico vendaServico);
}
