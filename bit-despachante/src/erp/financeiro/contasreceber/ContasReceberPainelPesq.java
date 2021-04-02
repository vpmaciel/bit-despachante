package erp.financeiro.contasreceber;

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
public final class ContasReceberPainelPesq extends JPanel {

	List<ContasReceber> contasRecebers = null;
	private final ContasReceberTm contasReceberTm;
	private final JTable table;

	public ContasReceberPainelPesq() {
		setBorder(Sis.getBordaPainel());

		contasRecebers = new LinkedList<>();
		contasReceberTm = new ContasReceberTm(contasRecebers);

		table = new JTable();
		table.setModel(contasReceberTm);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, ContasReceberTm.largura);
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

	public void atualizarGui(List<ContasReceber> contasRecebers) {
		contasReceberTm.setContasReceberList(contasRecebers);
		contasReceberTm.fireTableDataChanged();
	}

	public ContasReceberTm getContasReceberTableModel() {
		return contasReceberTm;
	}

	public void iniciarControlador() {
		ContasReceberSel listener = new ContasReceberSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(ContasReceber contasReceber) {
		contasRecebers = new LinkedList<>();
		try {
			contasRecebers = new LinkedList<>(ContasReceberFac.pesquisarRegistro(contasReceber));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(contasRecebers);
		return contasRecebers.size();
	}
}
