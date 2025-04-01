package erp.cliente;

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
public final class ClientePainelPesq extends JPanel {

    private final ClienteTm marcaTableModel;
    private final JTable table;
    List<Cliente> clienteList = null;

    public ClientePainelPesq() {
	setBorder(Sis.getBordaPainel());

	clienteList = new LinkedList<>();
	marcaTableModel = new ClienteTm(clienteList);

	table = new JTable();
	table.setModel(marcaTableModel);
	for (int c = 0; c < table.getColumnCount(); ++c) {
	    table.setDefaultRenderer(table.getColumnClass(c), Tabela.getDefaultTableCellRenderer());
	}
	Tabela.configurarLarguraColunasTabela(table, ClienteTm.largura);
	((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(SwingConstants.RIGHT);
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
	setLayout(new BorderLayout());
	add(scrollPane, "Center");
    }

    public void atualizarGui(List<Cliente> contaList) {
	marcaTableModel.setContaList(contaList);
	marcaTableModel.fireTableDataChanged();
    }

    public ClienteTm getContaTableModel() {
	return marcaTableModel;
    }

    public void iniciarControlador() {
	ClienteSel listener = new ClienteSel(table);
	table.getSelectionModel().addListSelectionListener(listener);
    }

    public int pesquisarRegistro(Cliente marca) {
	clienteList = new LinkedList<>();
	try {
	    clienteList = new LinkedList<>(ClienteFac.pesquisarRegistro(marca));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	atualizarGui(clienteList);
	return clienteList.size();
    }
}
