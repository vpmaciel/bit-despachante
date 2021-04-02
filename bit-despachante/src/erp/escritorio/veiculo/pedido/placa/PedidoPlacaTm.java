package erp.escritorio.veiculo.pedido.placa;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class PedidoPlacaTm extends AbstractTableModel {

	public static final int ID = 0;
	public static int[] largura;
	private static boolean[] podeEditar;
	private static TabelaModelo tabelaModelo = new TabelaModelo();
	static {
		tabelaModelo.adicionarColuna("ID", 0, 100);
		tabelaModelo.adicionarColuna("NOME", 1, 500);

		largura = new int[tabelaModelo.getTotalColunas()];
		podeEditar = new boolean[tabelaModelo.getTotalColunas()];
		for (int i = 0; i < tabelaModelo.getTotalColunas(); i++) {
			largura[i] = tabelaModelo.getLargura(i);
			podeEditar[i] = false;
		}
	}
	private PedidoPlaca pedidoPlaca;

	private List<PedidoPlaca> pedidoPlacaList = new LinkedList<>();

	public PedidoPlacaTm() {

	}

	public PedidoPlacaTm(List<PedidoPlaca> lista) {
		pedidoPlacaList.addAll(lista);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return Long.class;
		}
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return largura.length;
	}

	@Override
	public String getColumnName(int column) {
		return tabelaModelo.getNome(column);
	}

	public PedidoPlaca getPedidoPlaca(int linha) {
		if (pedidoPlacaList.size() > 0) {
			return pedidoPlacaList.get(linha);
		}
		return null;
	}

	public List<PedidoPlaca> getPedidoPlacaList() {
		return pedidoPlacaList;
	}

	@Override
	public int getRowCount() {
		return pedidoPlacaList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PedidoPlaca pedidoPlaca = pedidoPlacaList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return pedidoPlaca.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return pedidoPlaca.getNome();
		}
		return pedidoPlaca;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setPedidoPlacaList(List<PedidoPlaca> pedidoPlaca) {
		pedidoPlacaList = pedidoPlaca;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		pedidoPlaca = pedidoPlacaList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			pedidoPlaca.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			pedidoPlaca.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}