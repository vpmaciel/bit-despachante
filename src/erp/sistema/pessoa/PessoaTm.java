package erp.sistema.pessoa;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class PessoaTm extends AbstractTableModel {

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
	private Pessoa pessoa;

	private List<Pessoa> usuarioList = new LinkedList<>();

	public PessoaTm() {

	}

	public PessoaTm(List<Pessoa> lista) {
		usuarioList.addAll(lista);
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
		return usuarioList.size();
	}

	public Pessoa getPessoa(int linha) {
		if (usuarioList.size() > 0) {
			return usuarioList.get(linha);
		}
		return null;
	}

	public List<Pessoa> getPessoaList() {
		return usuarioList;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pessoa pessoa = usuarioList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return pessoa.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return pessoa.getNome();
		}
		return pessoa;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setPessoaList(List<Pessoa> pessoa) {
		usuarioList = pessoa;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		pessoa = usuarioList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			pessoa.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			pessoa.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}