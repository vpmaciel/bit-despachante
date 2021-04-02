package erp.negocio.veiculo.documento;

import java.util.Comparator;

public class VeiculoDocumentoComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((VeiculoDocumento) o1).getId().compareTo(((VeiculoDocumento) o2).getId());
		}
	}

	public class Placa implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((VeiculoDocumento) o1).getVeiculo().getPlaca()
					.compareToIgnoreCase(((VeiculoDocumento) o2).getVeiculo().getPlaca());
		}
	}

}
