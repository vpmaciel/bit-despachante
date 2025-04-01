package erp.servico;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainController;

final class ServicoSel implements ListSelectionListener {

    JTable table;

    ServicoSel(JTable table) {
	this.table = table;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
	if (!e.getValueIsAdjusting()) {
	    int[] selRows = table.getSelectedRows();
	    TableModel tm = table.getModel();
	    if (selRows.length > 0) {
		Servico servicoPesquisaRegistro = new Servico();
		servicoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ServicoTm.ID));

		if (table.getSelectedRow() != -1) {
		    Servico servico = ((List<Servico>) ServicoFac.pesquisarRegistro(servicoPesquisaRegistro))
			    .get(table.getSelectedRow());
		    ServicoTm servicoTm = (ServicoTm) table.getModel();
		    servicoTm.getMarca(table.getSelectedRow());

		    MainController.mostrarFrame(MainController.getServicoJan());
		    MainController.getServicoJan().getServicoCont().setServico(ServicoFac.getRegistro(servico));
		    MainController.getServicoJan().getServicoCont().atualizarGui();
		    MainController.getServicoJan().setFocusable(true);
		    MainController.getServicoJan().getTabbedPane().setSelectedIndex(0);
		}
	    }
	}
    }
}
