package erp.negocio.produto.marca;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class ProdutoMarcaTm extends AbstractTableModel {

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
	private ProdutoMarca produtoMarca;

	private List<ProdutoMarca> produtoMarcaList = new LinkedList<>();

	public ProdutoMarcaTm() {

	}

	public ProdutoMarcaTm(List<ProdutoMarca> lista) {
		produtoMarcaList.addAll(lista);
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

	public ProdutoMarca getProdutoMarca(int linha) {
		if (produtoMarcaList.size() > 0) {
			return produtoMarcaList.get(linha);
		}
		return null;
	}

	public List<ProdutoMarca> getProdutoMarcaList() {
		return produtoMarcaList;
	}

	@Override
	public int getRowCount() {
		return produtoMarcaList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ProdutoMarca produtoMarca = produtoMarcaList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return produtoMarca.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return produtoMarca.getNome();
		}
		return produtoMarca;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setProdutoMarcaList(List<ProdutoMarca> produtoMarca) {
		produtoMarcaList = produtoMarca;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		produtoMarca = produtoMarcaList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			produtoMarca.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			produtoMarca.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}