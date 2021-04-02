package erp.escritorio.veiculo.seguro;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class SeguroTm extends AbstractTableModel {

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
	private Seguro seguro;

	private List<Seguro> seguroList = new LinkedList<>();

	public SeguroTm() {

	}

	public SeguroTm(List<Seguro> lista) {
		seguroList.addAll(lista);
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

	@Override
	public int getRowCount() {
		return seguroList.size();
	}

	public Seguro getSeguro(int linha) {
		if (seguroList.size() > 0) {
			return seguroList.get(linha);
		}
		return null;
	}

	public List<Seguro> getSeguroList() {
		return seguroList;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Seguro seguro = seguroList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return seguro.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return seguro.getNome();
		}
		return seguro;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setSeguroList(List<Seguro> seguro) {
		seguroList = seguro;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		seguro = seguroList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			seguro.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			seguro.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}