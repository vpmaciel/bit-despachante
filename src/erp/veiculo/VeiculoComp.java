package erp.veiculo;

import java.util.Comparator;

public final class VeiculoComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Veiculo) o1).getMarca().compareTo(((Veiculo) o2).getMarca());
		}
	}
}
