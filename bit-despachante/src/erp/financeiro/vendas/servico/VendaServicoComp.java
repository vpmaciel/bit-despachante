package erp.financeiro.vendas.servico;

import java.util.Comparator;

public final class VendaServicoComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((VendaServico) o1).getId().compareTo(((VendaServico) o2).getId());
		}
	}
}
