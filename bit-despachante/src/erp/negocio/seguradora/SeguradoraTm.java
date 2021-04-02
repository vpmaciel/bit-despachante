package erp.negocio.seguradora;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class SeguradoraTm extends AbstractTableModel {

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
	private Seguradora seguradora;

	private List<Seguradora> seguradoraList = new LinkedList<>();

	public SeguradoraTm() {

	}

	public SeguradoraTm(List<Seguradora> lista) {
		seguradoraList.addAll(lista);
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
		return seguradoraList.size();
	}

	public Seguradora getSeguradora(int linha) {
		if (seguradoraList.size() > 0) {
			return seguradoraList.get(linha);
		}
		return null;
	}

	public List<Seguradora> getSeguradoraList() {
		return seguradoraList;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Seguradora seguradora = seguradoraList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return seguradora.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return seguradora.getNome();
		}
		return seguradora;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setSeguradoraList(List<Seguradora> seguradora) {
		seguradoraList = seguradora;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		seguradora = seguradoraList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			seguradora.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			seguradora.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}