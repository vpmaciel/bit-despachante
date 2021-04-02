package erp.negocio.servico;

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
public final class ServicoPainelPesq extends JPanel {

	List<Servico> servicos = null;
	private final ServicoTm servicoTm;
	private final JTable table;

	public ServicoPainelPesq() {
		setBorder(Sis.getBordaPainel());

		servicos = new LinkedList<>();
		servicoTm = new ServicoTm(servicos);

		table = new JTable();
		table.setModel(servicoTm);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, ServicoTm.largura);
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

	public void atualizarGui(List<Servico> servicos) {
		servicoTm.setServicoList(servicos);
		servicoTm.fireTableDataChanged();
	}

	public ServicoTm getServicoTableModel() {
		return servicoTm;
	}

	public void iniciarControlador() {
		ServicoSel listener = new ServicoSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(Servico servico) {
		servicos = new LinkedList<>();
		try {
			servicos = new LinkedList<>(ServicoFac.pesquisarRegistro(servico));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(servicos);
		return servicos.size();
	}
}
