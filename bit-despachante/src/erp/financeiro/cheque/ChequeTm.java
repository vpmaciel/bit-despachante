package erp.financeiro.cheque;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class ChequeTm extends AbstractTableModel {

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
	private Cheque cheque;

	private List<Cheque> chequeList = new LinkedList<>();

	public ChequeTm() {

	}

	public ChequeTm(List<Cheque> lista) {
		chequeList.addAll(lista);
	}

	public Cheque getCheque(int linha) {
		if (chequeList.size() > 0) {
			return chequeList.get(linha);
		}
		return null;
	}

	public List<Cheque> getChequeList() {
		return chequeList;
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
		return chequeList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cheque cheque = chequeList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return cheque.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return cheque.getNome();
		}
		return cheque;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setChequeList(List<Cheque> cheque) {
		chequeList = cheque;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		cheque = chequeList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			cheque.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			cheque.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}