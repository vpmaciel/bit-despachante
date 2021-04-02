package erp.financeiro.vendas.servico;

import java.util.Collection;

public final class VendaServicoFac {

	private static final VendaServicoDao vendaServicoDao = new VendaServicoImp();

	public static void deletarRegistro(VendaServico vendaServico) {

		vendaServicoDao.deletarRegistro(vendaServico);
	}

	public static Collection<VendaServico> getRegistro() {

		return vendaServicoDao.getRegistro();
	}

	public static VendaServico getRegistro(VendaServico vendaServico) {

		return vendaServicoDao.getRegistro(vendaServico);
	}

	public static Collection<VendaServico> pesquisarRegistro(VendaServico vendaServico) {

		return vendaServicoDao.pesquisarRegistro(vendaServico);
	}

	public static void salvarRegistro(VendaServico vendaServico) {

		vendaServicoDao.salvarRegistro(vendaServico);
	}

	private VendaServicoFac() {
	}
}
