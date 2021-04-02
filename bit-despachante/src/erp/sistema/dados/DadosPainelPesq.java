package erp.sistema.dados;

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
public final class DadosPainelPesq extends JPanel {

	List<Dados> contadorList = null;
	private final DadosTm contadorTm;
	private final JTable table;

	public DadosPainelPesq() {
		setBorder(Sis.getBordaPainel());

		contadorList = new LinkedList<>();
		contadorTm = new DadosTm(contadorList);

		table = new JTable();
		table.setModel(contadorTm);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, DadosTm.largura);
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

	public void atualizarGui(List<Dados> contadors) {
		contadorTm.setDadosList(contadors);
		contadorTm.fireTableDataChanged();
	}

	public DadosTm getDadosTableModel() {
		return contadorTm;
	}

	public void iniciarControlador() {
		DadosSel listener = new DadosSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(Dados contador) {
		contadorList = new LinkedList<>();
		try {
			contadorList = new LinkedList<>(DadosFac.pesquisarRegistro(contador));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(contadorList);
		return contadorList.size();
	}
}
