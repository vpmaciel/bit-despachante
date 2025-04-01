package erp.cliente;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainController;

final class ClienteSel implements ListSelectionListener {

    JTable table;

    ClienteSel(JTable table) {
	this.table = table;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
	if (!e.getValueIsAdjusting()) {
	    int[] selRows = table.getSelectedRows();
	    TableModel tm = table.getModel();
	    if (selRows.length > 0) {
		Cliente clientePesquisaRegistro = new Cliente();
		clientePesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ClienteTm.ID));

		if (table.getSelectedRow() != -1) {
		    Cliente cliente = ((List<Cliente>) ClienteFac.pesquisarRegistro(clientePesquisaRegistro))
			    .get(table.getSelectedRow());
		    ClienteTm clienteTm = (ClienteTm) table.getModel();
		    clienteTm.getCliente(table.getSelectedRow());

		    MainController.mostrarFrame(MainController.getClienteJan());
		    MainController.getClienteJan().getClienteCont().setCliente(ClienteFac.getRegistro(cliente));
		    MainController.getClienteJan().getClienteCont().atualizarGui();
		    MainController.getClienteJan().setFocusable(true);
		    MainController.getClienteJan().getTabbedPane().setSelectedIndex(0);
		}
	    }
	}
    }
}
