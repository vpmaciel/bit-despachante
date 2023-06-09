package erp.veiculo.marca;

import java.util.Comparator;

public final class MarcaComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Marca) o1).getDescricao().compareTo(((Marca) o2).getDescricao());
		}
	}
}
