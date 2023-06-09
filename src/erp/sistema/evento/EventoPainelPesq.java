package erp.sistema.evento;

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
public final class EventoPainelPesq extends JPanel {

	List<Evento> eventoList = null;
	private final EventoTm eventoTableModel;
	private final JTable table;

	public EventoPainelPesq() {
		setBorder(Sis.getBordaPainel());

		eventoList = new LinkedList<>();
		eventoTableModel = new EventoTm(eventoList);

		table = new JTable();
		table.setModel(eventoTableModel);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, EventoTm.largura);
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

	public void atualizarGui(List<Evento> eventos) {
		eventoTableModel.setEventoList(eventos);
		eventoTableModel.fireTableDataChanged();
	}

	public EventoTm getAgendaTableModel() {
		return eventoTableModel;
	}

	public void iniciarControlador() {
		EventoSel listener = new EventoSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(Evento evento) {
		eventoList = new LinkedList<>();
		try {
			eventoList = new LinkedList<>(EventoFac.pesquisarRegistro(evento));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(eventoList);
		return eventoList.size();
	}
}
