package erp.negocio.produto;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.Tabela;

@SuppressWarnings("serial")
public final class ProdutoPainelPesq extends JPanel {

	private List<Produto> listProduto = null;
	private final ProdutoTm produtoTm;
	private final JTable table;

	public ProdutoPainelPesq() {
		setBorder(Sis.getBordaPainel());

		listProduto = new LinkedList<>();
		produtoTm = new ProdutoTm(listProduto);

		table = new JTable();
		table.setModel(produtoTm);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, erp.negocio.produto.ProdutoTm.largura);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.setRowSelectionAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);
		table.setColumnSelectionAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(new Rectangle(0, 0, 720, 420));
		scrollPane.setViewportView(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		super.setLayout(new BorderLayout());
		super.add(scrollPane, "Center");
	}

	public void atualizarGui(List<Produto> listProduto) {
		produtoTm.setProdutoList(listProduto);
		produtoTm.fireTableDataChanged();
	}

	public ProdutoTm getProdutoTableModel() {
		return produtoTm;
	}

	public void iniciarControlador() {
		ProdutoSel listener = new ProdutoSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public long pesquisarRegistro(Produto produto) {
		listProduto = new LinkedList<>();
		try {
			listProduto = new LinkedList<>(ProdutoFac.pesquisarRegistro(produto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(listProduto);
		return listProduto.size();
	}
}
