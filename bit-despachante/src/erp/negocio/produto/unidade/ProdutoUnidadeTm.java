package erp.negocio.produto.unidade;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class ProdutoUnidadeTm extends AbstractTableModel {

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
	private ProdutoUnidade produtoUnidade;

	private List<ProdutoUnidade> produtoUnidadeList = new LinkedList<>();

	public ProdutoUnidadeTm() {

	}

	public ProdutoUnidadeTm(List<ProdutoUnidade> lista) {
		produtoUnidadeList.addAll(lista);
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

	public ProdutoUnidade getProdutoUnidade(int linha) {
		if (produtoUnidadeList.size() > 0) {
			return produtoUnidadeList.get(linha);
		}
		return null;
	}

	public List<ProdutoUnidade> getProdutoUnidadeList() {
		return produtoUnidadeList;
	}

	@Override
	public int getRowCount() {
		return produtoUnidadeList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ProdutoUnidade produtoUnidade = produtoUnidadeList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return produtoUnidade.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return produtoUnidade.getNome();
		}
		return produtoUnidade;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setProdutoUnidadeList(List<ProdutoUnidade> produtoUnidade) {
		produtoUnidadeList = produtoUnidade;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		produtoUnidade = produtoUnidadeList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			produtoUnidade.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			produtoUnidade.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}