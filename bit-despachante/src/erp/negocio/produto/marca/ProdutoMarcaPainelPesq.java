package erp.negocio.produto.marca;

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
public final class ProdutoMarcaPainelPesq extends JPanel {

	List<ProdutoMarca> produtoMarcas = null;
	private final ProdutoMarcaTm produtoMarcaTm;
	private final JTable table;

	public ProdutoMarcaPainelPesq() {
		setBorder(Sis.getBordaPainel());

		produtoMarcas = new LinkedList<>();
		produtoMarcaTm = new ProdutoMarcaTm(produtoMarcas);

		table = new JTable();
		table.setModel(produtoMarcaTm);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, ProdutoMarcaTm.largura);
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

	public void atualizarGui(List<ProdutoMarca> produtoMarcas) {
		produtoMarcaTm.setProdutoMarcaList(produtoMarcas);
		produtoMarcaTm.fireTableDataChanged();
	}

	public ProdutoMarcaTm getProdutoMarcaTableModel() {
		return produtoMarcaTm;
	}

	public void iniciarControlador() {
		ProdutoMarcaSel listener = new ProdutoMarcaSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(ProdutoMarca produtoMarca) {
		produtoMarcas = new LinkedList<>();
		try {
			produtoMarcas = new LinkedList<>(ProdutoMarcaFac.pesquisarRegistro(produtoMarca));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(produtoMarcas);
		return produtoMarcas.size();
	}
}
