package erp.sistema.pessoa;

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
public final class PessoaPainelPesq extends JPanel {

	private final PessoaTm agenciaTableModel;
	private final JTable table;
	List<Pessoa> usuarioList = null;

	public PessoaPainelPesq() {
		setBorder(Sis.getBordaPainel());

		usuarioList = new LinkedList<>();
		agenciaTableModel = new PessoaTm(usuarioList);

		table = new JTable();
		table.setModel(agenciaTableModel);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, PessoaTm.largura);
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

	public void atualizarGui(List<Pessoa> pessoas) {
		agenciaTableModel.setPessoaList(pessoas);
		agenciaTableModel.fireTableDataChanged();
	}

	public PessoaTm getPessoaTableModel() {
		return agenciaTableModel;
	}

	public void iniciarControlador() {
		PessoaSel listener = new PessoaSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(Pessoa pessoa) {
		usuarioList = new LinkedList<>();
		try {
			usuarioList = new LinkedList<>(PessoaFac.pesquisarRegistro(pessoa));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(usuarioList);
		return usuarioList.size();
	}
}