package erp.sistema.empresa;

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
public final class EmpresaPainelPesq extends JPanel {

	private final EmpresaTm agenciaTableModel;
	private final JTable table;
	List<Empresa> usuarioList = null;

	public EmpresaPainelPesq() {
		setBorder(Sis.getBordaPainel());

		usuarioList = new LinkedList<>();
		agenciaTableModel = new EmpresaTm(usuarioList);

		table = new JTable();
		table.setModel(agenciaTableModel);
		for (int c = 0; c < table.getColumnCount(); ++c) {
			table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
		}
		Tabela.configurarLarguraColunasTabela(table, EmpresaTm.largura);
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

	public void atualizarGui(List<Empresa> empresas) {
		agenciaTableModel.setEmpresaList(empresas);
		agenciaTableModel.fireTableDataChanged();
	}

	public EmpresaTm getEmpresaTableModel() {
		return agenciaTableModel;
	}

	public void iniciarControlador() {
		EmpresaSel listener = new EmpresaSel(table);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public int pesquisarRegistro(Empresa empresa) {
		usuarioList = new LinkedList<>();
		try {
			usuarioList = new LinkedList<>(EmpresaFac.pesquisarRegistro(empresa));
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarGui(usuarioList);
		return usuarioList.size();
	}
}
