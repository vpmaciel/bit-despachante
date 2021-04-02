package erp.financeiro.caixa;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class CaixaTm extends AbstractTableModel {

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
	private Caixa caixa;

	private List<Caixa> caixaList = new LinkedList<>();

	public CaixaTm() {

	}

	public CaixaTm(List<Caixa> lista) {
		caixaList.addAll(lista);
	}

	public Caixa getCaixa(int linha) {
		if (caixaList.size() > 0) {
			return caixaList.get(linha);
		}
		return null;
	}

	public List<Caixa> getCaixaList() {
		return caixaList;
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
		return caixaList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Caixa caixa = caixaList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return caixa.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return caixa.getUsuario();
		}
		return caixa;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setCaixaList(List<Caixa> caixa) {
		caixaList = caixa;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		caixa = caixaList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			caixa.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			caixa.setUsuario(aValue.toString());
		}

		fireTableDataChanged();
	}
}