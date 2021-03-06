package erp.negocio.contador;

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
public final class ContadorPainelPesq extends JPanel {

	List<Contador> contadorList = null;
	private final ContadorTm contadorTm;
	private final JTable table;

	public ContadorPainelPesq() {
		setBorder(Sis.getBordaPainel());

		contadorList = new LinkedList<>();
		contadorTm = new ContadorTm(contadorList);

		table = new JTable();
		table.setModel(contadorTm);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, ContadorTm.largura);
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

	public void atualizarGui(List<Contador> contadors) {
		contadorTm.setContadorList(contadors);
		contadorTm.fireTableDataChanged();
	}

	public ContadorTm getContadorTableModel() {
		return contadorTm;
	}

	public void iniciarControlador() {
		ContadorSel listener = new ContadorSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(Contador contador) {
		contadorList = new LinkedList<>();
		try {
			contadorList = new LinkedList<>(ContadorFac.pesquisarRegistro(contador));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(contadorList);
		return contadorList.size();
	}
}
