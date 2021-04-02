package erp.negocio.seguradora;

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
public final class SeguradoraPainelPesq extends JPanel {

	List<Seguradora> seguradoras = null;
	private final SeguradoraTm seguradoraTm;
	private final JTable table;

	public SeguradoraPainelPesq() {
		setBorder(Sis.getBordaPainel());

		seguradoras = new LinkedList<>();
		seguradoraTm = new SeguradoraTm(seguradoras);

		table = new JTable();
		table.setModel(seguradoraTm);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, SeguradoraTm.largura);
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

	public void atualizarGui(List<Seguradora> seguradoras) {
		seguradoraTm.setSeguradoraList(seguradoras);
		seguradoraTm.fireTableDataChanged();
	}

	public SeguradoraTm getSeguradoraTableModel() {
		return seguradoraTm;
	}

	public void iniciarControlador() {
		SeguradoraSel listener = new SeguradoraSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(Seguradora seguradora) {
		seguradoras = new LinkedList<>();
		try {
			seguradoras = new LinkedList<>(SeguradoraFac.pesquisarRegistro(seguradora));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(seguradoras);
		return seguradoras.size();
	}
}
