package erp.servico;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

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
				Servico marcaPesquisaRegistro = new Servico();
				marcaPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ServicoTm.ID));

				if (table.getSelectedRow() != -1) {
					Servico marca = ((List<Servico>) ServicoFac.pesquisarRegistro(marcaPesquisaRegistro))
							.get(table.getSelectedRow());
					ServicoTm marcaTm = (ServicoTm) table.getModel();
					marcaTm.getMarca(table.getSelectedRow());

					MainControl.mostrarFrame(MainControl.getServicoJan());
					MainControl.getServicoJan().getContaCont().setModelo(ServicoFac.getRegistro(marca));
					MainControl.getServicoJan().getContaCont().atualizarGui();
					MainControl.getServicoJan().setFocusable(true);
					MainControl.getServicoJan().getTabbedPane().setSelectedIndex(0);
				}
			}
		}
	}
}
