package erp.veiculo;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class VeiculoTm extends AbstractTableModel {

	public static final int ID = 0;
	public static int[] largura;
	private static boolean[] podeEditar;
	private static TabelaModelo tabelaModelo = new TabelaModelo();
	static {
		tabelaModelo.adicionarColuna("ID", 0, 50);
		tabelaModelo.adicionarColuna("PLACA", 1, 100);
		tabelaModelo.adicionarColuna("MARCA", 2, 600);
		tabelaModelo.adicionarColuna("MODELO", 3, 600);

		largura = new int[tabelaModelo.getTotalColunas()];
		podeEditar = new boolean[tabelaModelo.getTotalColunas()];
		for (int i = 0; i < tabelaModelo.getTotalColunas(); i++) {
			largura[i] = tabelaModelo.getLargura(i);
			podeEditar[i] = false;
		}
	}
	private Veiculo marca;

	private List<Veiculo> marcaList = new LinkedList<>();

	public VeiculoTm() {

	}

	public VeiculoTm(List<Veiculo> lista) {
		marcaList.addAll(lista);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return Long.class;
		}

		if (tabelaModelo.getNome(columnIndex).equals("PLACA") || tabelaModelo.getNome(columnIndex).equals("MARCA") || tabelaModelo.getNome(columnIndex).equals("MODELO")) {
			return String.class;
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

	public List<Veiculo> getContaList() {
		return marcaList;
	}

	public Veiculo getMarca(int linha) {
		if (marcaList.size() > 0) {
			return marcaList.get(linha);
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return marcaList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Veiculo marca = marcaList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return marca.getId();
		}

		if (tabelaModelo.getNome(columnIndex).equals("PLACA")) {
			return marca.getPlaca();
		}

		if (tabelaModelo.getNome(columnIndex).equals("MARCA")) {
			return marca.getMarca();
		}

		if (tabelaModelo.getNome(columnIndex).equals("MODELO")) {
			return marca.getModelo();
		}

		return marca;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setContaList(List<Veiculo> marca) {
		marcaList = marca;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		marca = marcaList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			marca.setId(Long.parseLong(aValue.toString()));
		}

		if (tabelaModelo.getNome(columnIndex).equals("PLACA")) {
			marca.setPlaca(aValue.toString());
		}

		if (tabelaModelo.getNome(columnIndex).equals("MARCA")) {
			marca.setMarca(aValue.toString());
		}

		if (tabelaModelo.getNome(columnIndex).equals("MODELO")) {
			marca.setModelo(aValue.toString());
		}

		fireTableDataChanged();
	}
}