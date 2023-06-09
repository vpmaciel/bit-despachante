package erp.sistema.atividade;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class AtividadeTm extends AbstractTableModel {

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
	private Atividade atividade;

	private List<Atividade> atividadeList = new LinkedList<>();

	public AtividadeTm() {

	}

	public AtividadeTm(List<Atividade> lista) {
		atividadeList.addAll(lista);
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
		return atividadeList.size();
	}

	public Atividade getAtividade(int linha) {
		if (atividadeList.size() > 0) {
			return atividadeList.get(linha);
		}
		return null;
	}

	public List<Atividade> getUsuarioList() {
		return atividadeList;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Atividade atividade = atividadeList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return atividade.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return atividade.getNome();
		}
		return atividade;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setUsuarioList(List<Atividade> atividade) {
		atividadeList = atividade;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		atividade = atividadeList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			atividade.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			atividade.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}