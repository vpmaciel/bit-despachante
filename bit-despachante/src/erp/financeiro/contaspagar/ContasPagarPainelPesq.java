package erp.financeiro.contaspagar;

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
public final class ContasPagarPainelPesq extends JPanel {

	List<ContasPagar> contasPagars = null;
	private final ContasPagarTm contasPagarTm;
	private final JTable table;

	public ContasPagarPainelPesq() {
		setBorder(Sis.getBordaPainel());

		contasPagars = new LinkedList<>();
		contasPagarTm = new ContasPagarTm(contasPagars);

		table = new JTable();
		table.setModel(contasPagarTm);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, ContasPagarTm.largura);
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

	public void atualizarGui(List<ContasPagar> contasPagars) {
		contasPagarTm.setContasPagarList(contasPagars);
		contasPagarTm.fireTableDataChanged();
	}

	public ContasPagarTm getContasPagarTableModel() {
		return contasPagarTm;
	}

	public void iniciarControlador() {
		ContasPagarSel listener = new ContasPagarSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(ContasPagar contasPagar) {
		contasPagars = new LinkedList<>();
		try {
			contasPagars = new LinkedList<>(ContasPagarFac.pesquisarRegistro(contasPagar));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(contasPagars);
		return contasPagars.size();
	}
}
